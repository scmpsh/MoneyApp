package wallet.app.repository

import org.springframework.stereotype.Repository
import wallet.app.entity.Income

@Repository
interface IncomeRepository : BaseRepository<Income, String> {
}