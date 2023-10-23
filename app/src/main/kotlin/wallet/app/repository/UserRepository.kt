package wallet.app.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import wallet.app.entity.User

@Repository
interface UserRepository: JpaRepository<User, String>