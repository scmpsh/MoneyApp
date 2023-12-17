package wallet.app.entity

import jakarta.persistence.*
import wallet.app.enums.RoleType

@Entity
@Table(name = "user")
class User(
    @Column(name = "login") var login: String,
    @Column(name = "email") var email: String,
    @Column(name = "password") var password: String,
    @Column(name = "first_name") var firstName: String?,
    @Column(name = "last_name") var lastName: String?,
    @Enumerated(EnumType.STRING) var role: RoleType
) : BaseUpdatableEntity<String>()