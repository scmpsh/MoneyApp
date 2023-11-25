package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wallet.app.dto.AuthRequestDto
import wallet.app.dto.AuthResponseDto
import wallet.app.service.AuthenticationService

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping
    fun authenticate(@RequestBody authRequest: AuthRequestDto): ResponseEntity<AuthResponseDto> =
        ResponseEntity.ok(authenticationService.authentication(authRequest))
}