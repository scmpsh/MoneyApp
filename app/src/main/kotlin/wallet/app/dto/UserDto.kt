package wallet.app.dto

data class UserDto(
    private var login: String,
    private var password: String,
    private var firstName: String,
    private var lastName: String,
    private var email: String
)