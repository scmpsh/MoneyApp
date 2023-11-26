package wallet.app.enums

import org.springframework.security.core.authority.SimpleGrantedAuthority
import wallet.app.enums.PermissionType.*
import java.util.stream.Collectors


enum class RoleType(private val permissions: MutableSet<PermissionType>) {
    USER(
        mutableSetOf(
            USER_READ,
            USER_UPDATE,
            USER_CREATE
        )
    ),
    ADMIN(
        mutableSetOf(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE,
        )
    );

    val authorities: MutableCollection<SimpleGrantedAuthority>
        get() {
            val authorities = permissions
                .stream()
                .map { permission -> SimpleGrantedAuthority(permission.permission) }
                .collect(Collectors.toList())
            authorities.add(SimpleGrantedAuthority("ROLE_$name"))
            return authorities
        }
}