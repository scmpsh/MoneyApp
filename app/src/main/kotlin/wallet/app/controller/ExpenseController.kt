package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wallet.app.dto.ExpenseDto
import wallet.app.entity.Expense
import wallet.app.service.ExpenseService

@RestController
@RequestMapping("/expense")
class ExpenseController(private val expenseService: ExpenseService) {

    @PostMapping("/save")
    fun saveExpense(@RequestBody expenseDto: ExpenseDto): ResponseEntity<Expense> {
        return ResponseEntity.ok(expenseService.saveExpense(expenseDto))
    }


}