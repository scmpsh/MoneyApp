package wallet.app.entity.dictionary

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import org.hibernate.annotations.CreationTimestamp
import wallet.app.utils.UserHolder
import java.io.Serializable
import java.time.OffsetDateTime

@MappedSuperclass
abstract class BaseDictionary : Serializable {

    @Column(name = "create_date")
    @CreationTimestamp
    var createDate: OffsetDateTime? = OffsetDateTime.now()

    @Column(name = "create_user")
    var createUser: String? = "SYSTEM"

    @PrePersist
    fun setCreatedBy() {
        this.createDate = OffsetDateTime.now()
        this.createUser = UserHolder.getUserName()
    }
}