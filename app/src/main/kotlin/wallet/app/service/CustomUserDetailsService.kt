package wallet.app.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = wallet.app.entity.User

@Service
class CustomUserDetailsService(
    private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userService.getUserByLogin(username)
            ?.mapUserDetails()
            ?: throw UsernameNotFoundException("Not found by login $username")

    private fun ApplicationUser.mapUserDetails(): UserDetails =
        User.builder()
            .username(this.email.uppercase())
            .password(this.password)
            .authorities(this.role.authorities)
            .build()
}
