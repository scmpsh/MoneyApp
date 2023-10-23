package wallet.app.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import wallet.app.dto.IncomeDto
import wallet.app.entity.Income
import wallet.app.repository.CategoryRepository
import wallet.app.repository.UserRepository

@Component
class IncomeMapper(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) : IMapper<Income, IncomeDto> {
    override fun toDto(entity: Income): IncomeDto {
        return IncomeDto(
            entity.amount,
            entity.userId.id!!,
            entity.categoryCode.code
        )
    }

    override fun fromDto(dto: IncomeDto): Income {
        return Income(
            dto.amount,
            userRepository.findByIdOrNull(dto.userId)!!,
            categoryRepository.findByIdOrNull(dto.categoryCode)!!
        )
    }
}