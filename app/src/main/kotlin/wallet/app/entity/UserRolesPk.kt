package wallet.app.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class UserRolesPk(
    @Column(name = "user_id") var userId: String,
    @Column(name = "role_id") var roleId: String
) : Serializable