package wallet.app.dto

data class UserDto(
    var id: String?,
    var login: String?,
    var email: String,
    var password: String,
    var firstName: String?,
    var lastName: String?,
    var role: String
)