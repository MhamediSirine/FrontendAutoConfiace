package centre.elife.fronted_autoconfiance.data.models

data class LoginResponseModel(
    val message: String,
    val data: LoginDataModel
)

data class LoginDataModel(
    val token: String,
    val email: String,
    val role: String
)