package wallet.app.dto


data class ExpenseDto(
    var amount: Double,
    var userId: String,
    var categoryCode: String
)