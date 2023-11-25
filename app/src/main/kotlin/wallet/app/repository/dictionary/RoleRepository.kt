package wallet.app.repository.dictionary

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wallet.app.entity.dictionary.Role

@Repository
interface RoleRepository : JpaRepository<Role, String> {
}