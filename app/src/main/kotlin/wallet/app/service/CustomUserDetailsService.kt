package wallet.app.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import wallet.app.enums.RoleType

typealias ApplicationUser = wallet.app.entity.User

@Service
class CustomUserDetailsService(
    private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userService.getUserByEmail(username)
            ?.mapUserDetails()!!

    private fun ApplicationUser.mapUserDetails(): UserDetails =
        User.builder()
            .username(this.email.uppercase())
            .password(this.password)
            .roles(this.roles.stream().filter { v -> v.role.code == RoleType.ADMIN.name }.map { v -> v.role.code }
                .findFirst().get())
            .build()
}