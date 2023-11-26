package wallet.app.mapper

import org.springframework.stereotype.Component
import wallet.app.dto.UserDto
import wallet.app.entity.User
import wallet.app.enums.RoleType

@Component
class UserMapper : IMapper<User, UserDto> {
    override fun toDto(entity: User): UserDto {
        return UserDto(
            entity.id,
            entity.login.uppercase(),
            entity.email.uppercase(),
            entity.password,
            entity.firstName.let { v ->
                if (v.isNullOrBlank()) null else v.uppercase()
            },
            entity.lastName.let { v ->
                if (v.isNullOrBlank()) null else v.uppercase()
            },
            entity.role.name
        )
    }

    override fun fromDto(dto: UserDto): User {
        return User(
            dto.id,
            dto.login.let { v ->
                if (v.isNullOrBlank()) dto.email.uppercase() else v.uppercase()
            },
            dto.email.uppercase(),
            dto.password,
            dto.firstName.let { v ->
                if (v.isNullOrBlank()) null else v.uppercase()
            },
            dto.lastName.let { v ->
                if (v.isNullOrBlank()) null else v.uppercase()
            },
            RoleType.valueOf(dto.role)
        )
    }
}