package wallet.app.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import wallet.app.dto.IncomeDto
import wallet.app.entity.Income
import wallet.app.service.IncomeService

@RestController
@RequestMapping("/income")
class IncomeController(
    private val incomeService: IncomeService
) {

    @PostMapping("/save")
    fun saveIncome(@RequestBody incomeDto: IncomeDto): ResponseEntity<Income> {
        return ResponseEntity.ok(incomeService.saveIncome(incomeDto))
    }
}