package wallet.app.entity.dictionary

import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import java.io.Serializable

@MappedSuperclass
abstract class BaseDictionary(@Id @Column(name = "code") var code: String,
                              @Column(name = "name") var name: String,
                              @Column(name = "create_date") @CreatedDate var createDate: String) : Serializable