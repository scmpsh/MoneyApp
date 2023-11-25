package wallet.app.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import wallet.app.dto.IncomeDto
import wallet.app.entity.Income
import wallet.app.mapper.IMapper
import wallet.app.repository.IncomeRepository

@Service
class IncomeService(
    private val incomeRepository: IncomeRepository,
    private val mapper: IMapper<Income, IncomeDto>
) {

    @Transactional
    fun saveIncome(incomeDto: IncomeDto): Income {
        val result = mapper.fromDto(incomeDto)
        return incomeRepository.save(result)
    }
}