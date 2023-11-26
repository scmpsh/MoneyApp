package wallet.app.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import wallet.app.dto.AuthRequestDto
import wallet.app.dto.AuthResponseDto
import wallet.app.dto.RefreshTokenRequestDto
import wallet.app.service.AuthenticationService
import wallet.app.service.UserService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthRequestDto): ResponseEntity<AuthResponseDto> {
        val response = ResponseEntity.ok(authenticationService.authentication(authRequest))
        userService.saveUser(authRequest)
        return response
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: RefreshTokenRequestDto
    ): AuthResponseDto =
        authenticationService.refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token")

    private fun AuthResponseDto.mapToTokenResponse(): AuthResponseDto =
        AuthResponseDto(
            login = this.login,
            accessToken = this.accessToken,
            expiredTimeAccessToken = this.expiredTimeAccessToken,
            refreshToken = this.refreshToken,
            expiredTimeRefreshToken = this.expiredTimeRefreshToken
        )
}

