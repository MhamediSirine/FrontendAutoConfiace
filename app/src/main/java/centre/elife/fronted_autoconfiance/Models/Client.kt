package centre.elife.fronted_autoconfiance.Models

data class Client(
    val name: String,
    val lastName: String,
    val address: String,
    val email: String,
    val password: String,
    val resetCode: String,
    val number: String
)
