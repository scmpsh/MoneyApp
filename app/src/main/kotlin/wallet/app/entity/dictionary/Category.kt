package wallet.app.entity.dictionary

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "Category")
class Category(code: String, name: String, createDate: String) : BaseDictionary(code, name, createDate)