package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping("/all")
    fun getAllUsers(): List<User> =
        userService.getAllUsers()
}