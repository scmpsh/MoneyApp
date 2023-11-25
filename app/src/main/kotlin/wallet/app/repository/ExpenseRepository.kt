package wallet.app.repository

import org.springframework.stereotype.Repository
import wallet.app.entity.Expense

@Repository
interface ExpenseRepository : BaseRepository<Expense, String>