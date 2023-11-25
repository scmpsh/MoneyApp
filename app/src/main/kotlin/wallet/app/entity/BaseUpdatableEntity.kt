package wallet.app.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Version
import org.hibernate.annotations.UpdateTimestamp
import wallet.app.utils.UserHolder
import java.io.Serializable
import java.time.OffsetDateTime

@MappedSuperclass
abstract class BaseUpdatableEntity<T : Serializable> : BaseEntity<T>() {

    @Column(name = "record_status", nullable = false)
    var recordStatus: Boolean? = true

    @Column(name = "version")
    @Version
    var version: Int? = 0

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    var updateDate: OffsetDateTime? = OffsetDateTime.now()

    @Column(name = "update_user")
    var updateUser: String? = "DEV"

    override fun setCreatedBy() {
        super.setCreatedBy()
        this.updateUser = UserHolder.getUserName()
    }

    @PreUpdate
    fun setUpdatedBy() {
        this.updateDate = OffsetDateTime.now()
        this.updateUser = UserHolder.getUserName()
    }
}