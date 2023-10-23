package wallet.app.mapper

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import wallet.app.dto.ExpenseDto
import wallet.app.entity.Expense
import wallet.app.repository.CategoryRepository
import wallet.app.repository.UserRepository

@Component
class ExpenseMapper(
    private val userRepository: UserRepository,
    private val categoryRepository: CategoryRepository
) : IMapper<Expense, ExpenseDto> {
    override fun toDto(entity: Expense): ExpenseDto {
        return ExpenseDto(
            entity.amount,
            entity.userId.id!!,
            entity.categoryCode.code
        )
    }

    override fun fromDto(dto: ExpenseDto): Expense {
        return Expense(
            dto.amount,
            userRepository.findByIdOrNull(dto.userId)!!,
            categoryRepository.findByIdOrNull(dto.categoryCode)!!
        )
    }
}