package wallet.app.repository

import org.springframework.stereotype.Repository
import wallet.app.entity.User

@Repository
interface UserRepository : BaseRepository<User, String> {
    fun getUserByEmail(email: String): User?
}