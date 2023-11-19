package wallet.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "user")
class User(
    @Column(name = "email") var email: String,
    @Column(name = "password") var password: String,
    @Column(name = "first_name") var firstName: String,
    @Column(name = "last_name") var lastName: String
) : BaseUpdatableEntity()