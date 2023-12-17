package wallet.app.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import wallet.app.dto.IncomeDto
import wallet.app.entity.Income
import wallet.app.repository.UserRepository
import wallet.app.repository.dictionary.CategoryRepository

@Component
class IncomeMapper(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) : IMapper<Income, IncomeDto> {
    override fun toDto(entity: Income): IncomeDto {
        return IncomeDto(
            entity.amount,
            entity.userId.id
                ?: throw IllegalArgumentException("Income entity with code: ${entity.categoryCode} can't map because user id is null"),
            entity.categoryCode.code
        )
    }

    override fun fromDto(dto: IncomeDto): Income {
        return Income(
            dto.amount,
            userRepository.findByIdOrNull(dto.userId)
                ?: throw IllegalArgumentException("Income dto with code: ${dto.categoryCode} can't map because user id is null"),
            categoryRepository.findByIdOrNull(dto.categoryCode)
                ?: throw IllegalArgumentException("Income dto with code: ${dto.categoryCode} can't map because user id is null")
        )
    }
}