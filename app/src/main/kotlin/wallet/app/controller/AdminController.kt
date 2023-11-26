package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wallet.app.entity.User
import wallet.app.service.UserService

@RestController
@RequestMapping("/admin")
class AdminController(
    private val userService: UserService
) {

    @GetMapping("/users")
    fun getAllUsers(): ResponseEntity<List<User>> =
        ResponseEntity.ok().body(userService.getAllUsers())
}