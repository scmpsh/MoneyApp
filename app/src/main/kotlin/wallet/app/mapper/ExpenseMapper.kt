package wallet.app.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import wallet.app.dto.ExpenseDto
import wallet.app.entity.Expense
import wallet.app.repository.UserRepository
import wallet.app.repository.dictionary.CategoryRepository

@Component
class ExpenseMapper(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) : IMapper<Expense, ExpenseDto> {
    override fun toDto(entity: Expense): ExpenseDto {
        return ExpenseDto(
            entity.amount,
            entity.userId.id
                ?: throw IllegalArgumentException("Expense entity with code: ${entity.categoryCode} can't map because user id is null"),
            entity.categoryCode.code
        )
    }

    override fun fromDto(dto: ExpenseDto): Expense {
        return Expense(
            dto.amount,
            userRepository.findByIdOrNull(dto.userId)
                ?: throw IllegalArgumentException("Expense dto with code: ${dto.categoryCode} can't map because user id is null"),
            categoryRepository.findByIdOrNull(dto.categoryCode)
                ?: throw IllegalArgumentException("Expense dto with code: ${dto.categoryCode} can't map because user id is null")
        )
    }
}