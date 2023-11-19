package wallet.app.mapper

import org.springframework.stereotype.Component
import wallet.app.dto.UserDto
import wallet.app.entity.User

@Component
class UserMapper : IMapper<User, UserDto> {
    override fun toDto(entity: User): UserDto {
        return UserDto(
            entity.email,
            entity.password,
            entity.firstName,
            entity.lastName
        )
    }

    override fun fromDto(dto: UserDto): User {
        return User(
            dto.email,
            dto.password,
            dto.firstName,
            dto.lastName,
        )
    }
}