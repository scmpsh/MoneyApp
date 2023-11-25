package wallet.app.mapper

import org.springframework.stereotype.Component
import wallet.app.dto.UserDto
import wallet.app.entity.User
import wallet.app.entity.UserRolesPk
import wallet.app.repository.UserRolesRepository
import java.util.stream.Collectors

@Component
class UserMapper(
    private val userRolesRepository: UserRolesRepository
) : IMapper<User, UserDto> {
    override fun toDto(entity: User): UserDto {
        return UserDto(
            entity.id,
            entity.login.uppercase(),
            entity.email.uppercase(),
            entity.password,
            entity.firstName.uppercase(),
            entity.lastName.uppercase(),
            entity.roles.stream().map { v -> v.role.code }.collect(Collectors.toList())
        )
    }

    override fun fromDto(dto: UserDto): User {
        return User(
            dto.id,
            if (dto.login.isNullOrBlank()) dto.email.uppercase() else dto.login.uppercase(),
            dto.email.uppercase(),
            dto.password,
            dto.firstName.uppercase(),
            dto.lastName.uppercase(),
            userRolesRepository.findAllById(dto.roles.stream().map { v -> UserRolesPk(dto.id!!, v.uppercase()) }.toList())
        )
    }
}