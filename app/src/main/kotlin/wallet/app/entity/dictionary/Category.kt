package wallet.app.entity.dictionary

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "category")
class Category(
    @Id @Column(name = "code") var code: String,
    @Column(name = "name") var name: String
) : BaseDictionary()