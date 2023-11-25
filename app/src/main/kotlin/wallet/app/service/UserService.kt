package wallet.app.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wallet.app.dto.UserDto
import wallet.app.entity.User
import wallet.app.mapper.IMapper
import wallet.app.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val encoder: PasswordEncoder,
    private val mapper: IMapper<User, UserDto>
) {

    @Transactional
    fun saveUser(userDto: UserDto): User {
        val user = userDto.copy(password = encoder.encode(userDto.password))
        return userRepository.save(mapper.fromDto(user))
    }

    @Transactional
    fun getUserByEmail(email: String): User? {
        return userRepository.getUserByEmail(email)
    }

    @Transactional
    fun getAllUsers(): List<User> =
        userRepository.findAll()

}