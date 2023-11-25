package wallet.app.repository

import org.springframework.stereotype.Repository
import wallet.app.entity.UserRoles
import wallet.app.entity.UserRolesPk

@Repository
interface UserRolesRepository : BaseRepository<UserRoles, UserRolesPk> {
}