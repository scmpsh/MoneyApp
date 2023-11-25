package wallet.app.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column(name = "id") override var id: String?,
    @Column(name = "login") var login: String,
    @Column(name = "email") var email: String,
    @Column(name = "password") var password: String,
    @Column(name = "first_name") var firstName: String,
    @Column(name = "last_name") var lastName: String,
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER) var roles: List<UserRoles>
) : BaseUpdatableEntity<String>()