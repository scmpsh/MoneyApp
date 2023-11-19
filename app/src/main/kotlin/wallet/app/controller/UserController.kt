package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wallet.app.dto.UserDto
import wallet.app.entity.User
import wallet.app.service.UserService

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/save")
    fun saveUser(@RequestBody userDto: UserDto): ResponseEntity<User> {

        return ResponseEntity.ok(userService.saveUser(userDto))
    }
}