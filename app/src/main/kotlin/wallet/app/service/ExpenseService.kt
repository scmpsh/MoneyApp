package wallet.app.service

import org.springframework.stereotype.Service
import wallet.app.dto.ExpenseDto
import wallet.app.entity.Expense
import wallet.app.mapper.IMapper
import wallet.app.repository.ExpenseRepository

@Service
class ExpenseService(
    private val expenseRepository: ExpenseRepository,
    private val mapper: IMapper<Expense, ExpenseDto>
) {

    fun saveExpense(expenseDto: ExpenseDto): Expense {
        val result = mapper.fromDto(expenseDto)
        return expenseRepository.save(result)
    }
}