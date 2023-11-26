package wallet.app.dto

data class AuthResponseDto(
    val login: String,
    val accessToken: String,
    val expiredTimeAccessToken: String,
    val refreshToken: String,
    val expiredTimeRefreshToken: String
)
