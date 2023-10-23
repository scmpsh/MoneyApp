package wallet.app.entity

import jakarta.persistence.*
import wallet.app.entity.dictionary.Category

@Entity
@Table(name = "expense")
class Expense(
    @Column(name = "amount") val amount: Double,
    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id") val userId: User,
    @ManyToOne @JoinColumn(name = "category_code", referencedColumnName = "code") val categoryCode: Category
) : BaseUpdatableEntity()