package wallet.app.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import wallet.app.config.JwtProperties
import wallet.app.dto.AuthRequestDto
import wallet.app.dto.AuthResponseDto
import java.util.*

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties
) {

    fun authentication(authRequestDto: AuthRequestDto): AuthResponseDto {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequestDto.login.uppercase(),
                authRequestDto.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequestDto.login.uppercase())
        val accessToken = tokenService.generate(
            userDetails = user,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpectation)
        )
        return AuthResponseDto(
            login = user.username.uppercase(),
            accessToken = accessToken
        )
    }
}
