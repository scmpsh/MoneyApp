package wallet.app.entity

import jakarta.persistence.*
import wallet.app.entity.dictionary.Role
import java.io.Serializable

@Entity
@Table(name = "user_roles")
class UserRoles(
    @EmbeddedId var id: UserRolesPk,
    @ManyToOne @MapsId("userId") @JoinColumn(name = "user_id") var user: User,
    @ManyToOne @MapsId("roleId") @JoinColumn(name = "role_id") var role: Role
) : Serializable