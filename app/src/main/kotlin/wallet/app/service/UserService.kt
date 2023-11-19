package wallet.app.service

import org.springframework.stereotype.Service
import wallet.app.dto.UserDto
import wallet.app.entity.User
import wallet.app.mapper.IMapper
import wallet.app.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val mapper: IMapper<User, UserDto>
) {

    fun saveUser(userDto: UserDto): User {
        return userRepository.save(mapper.fromDto(userDto))
    }
}