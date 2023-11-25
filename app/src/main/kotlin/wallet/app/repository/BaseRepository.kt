package wallet.app.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.findByIdOrNull

@NoRepositoryBean
interface BaseRepository<E, I> : JpaRepository<E, I> {

    fun getEntityById(id: I): E? = findByIdOrNull(id)

}