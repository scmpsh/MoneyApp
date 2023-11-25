package wallet.app.entity

import jakarta.persistence.*
import wallet.app.entity.dictionary.Category

@Entity
@Table(name = "income")
class Income(
    @Column(name = "amount") var amount: Double,
    @ManyToOne @JoinColumn(name = "user_id", referencedColumnName = "id") var userId: User,
    @ManyToOne @JoinColumn(name = "category_code", referencedColumnName = "code") var categoryCode: Category
) : BaseUpdatableEntity<String>()