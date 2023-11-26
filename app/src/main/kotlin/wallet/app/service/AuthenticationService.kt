package wallet.app.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import wallet.app.config.JwtProperties
import wallet.app.dto.AuthRequestDto
import wallet.app.dto.AuthResponseDto
import wallet.app.repository.RefreshTokenRepository
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun authentication(authRequestDto: AuthRequestDto): AuthResponseDto {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequestDto.login.uppercase(),
                authRequestDto.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequestDto.login.uppercase())
        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)

        refreshTokenRepository.save(refreshToken, user)

        return AuthResponseDto(
            login = user.username.uppercase(),
            accessToken = accessToken,
            expiredTimeAccessToken = tokenService.extractExpiration(accessToken),
            refreshToken = refreshToken,
            expiredTimeRefreshToken = tokenService.extractExpiration(refreshToken)
        )
    }

    private fun generateAccessToken(userDetails: UserDetails): String =
        tokenService.generate(
            userDetails = userDetails,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpectation)
        )

    private fun generateRefreshToken(userDetails: UserDetails): String =
        tokenService.generate(
            userDetails = userDetails,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpectation)
        )

    fun refreshAccessToken(token: String): AuthResponseDto? {
        val extractedLogin = tokenService.extractLogin(token)

        return extractedLogin?.let { login ->
            val currentUserDetails = userDetailsService.loadUserByUsername(login)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(token)
            val newAccessToken = generateAccessToken(currentUserDetails)
            val newRefreshToken = generateRefreshToken(currentUserDetails)

            refreshTokenRepository.remove(token)
            refreshTokenRepository.save(newRefreshToken, currentUserDetails)

            if (!tokenService.isExpired(token) && currentUserDetails.username == refreshTokenUserDetails?.username)
                AuthResponseDto(
                    login,
                    newAccessToken,
                    "",
                    newRefreshToken,
                    ""
                )
            else
                null

        }
    }
}
