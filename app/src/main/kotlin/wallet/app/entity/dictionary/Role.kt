package wallet.app.entity.dictionary

import jakarta.persistence.*
import wallet.app.entity.UserRoles

@Entity
@Table(name = "role")
class Role(
    @Id @Column(name = "code") var code: String,
    @Column(name = "name") var name: String,
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER) var user: List<UserRoles>
) : BaseDictionary()