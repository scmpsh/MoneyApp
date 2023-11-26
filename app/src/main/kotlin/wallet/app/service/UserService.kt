package wallet.app.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wallet.app.dto.AuthRequestDto
import wallet.app.dto.UserDto
import wallet.app.entity.User
import wallet.app.enums.RoleType
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
    fun saveUser(requestDto: AuthRequestDto): User? {
        val dbEntity = userRepository.getUserByLogin(requestDto.login)
        if (dbEntity != null) {
            return userRepository.save(requestDto.mapToUser())
        }
        return null
    }

    private fun AuthRequestDto.mapToUser(): User =
        User(
            null,
            this.login,
            this.login,
            this.password,
            null,
            null,
            RoleType.USER
        )

    @Transactional
    fun getUserByLogin(login: String): User? {
        return userRepository.getUserByLogin(login)
    }

    @Transactional
    fun getAllUsers(): List<User> =
        userRepository.findAll()

}