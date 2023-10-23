package wallet.app.utils

import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import java.util.UUID


class WalletUuidGenerator : IdentifierGenerator {
    override fun generate(session: SharedSessionContractImplementor?, `object`: Any?): String {
       return UUID.randomUUID().toString()
    }
}