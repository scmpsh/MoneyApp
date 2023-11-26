package wallet.app.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.util.ProxyUtils
import org.springframework.lang.NonNull
import wallet.app.utils.UserHolder
import java.io.Serializable
import java.time.OffsetDateTime

@MappedSuperclass
abstract class BaseEntity<T : Serializable> : Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "wallet.app.utils.WalletUuidGenerator")
    @NonNull
    open var id: T? = null

    @Column(name = "create_date")
    @CreationTimestamp
    var createDate: OffsetDateTime? = OffsetDateTime.now()

    @Column(name = "create_user")
    var createUser: String? = "SYSTEM"

    @PrePersist
    open fun setCreatedBy() {
        createDate = OffsetDateTime.now()
        this.createUser = UserHolder.getUserName().let {
            if (it.isNullOrBlank()) "SYSTEM" else UserHolder.getUserName()
        }
    }

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return this.id != null && this.id == other.id
    }

    override fun hashCode(): Int {
        return if (this.id != null) this.id.hashCode() else 0
    }

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }

}