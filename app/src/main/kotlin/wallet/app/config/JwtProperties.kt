package wallet.app.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("settings.jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpectation: Long,
    val refreshTokenExpectation: Long
)