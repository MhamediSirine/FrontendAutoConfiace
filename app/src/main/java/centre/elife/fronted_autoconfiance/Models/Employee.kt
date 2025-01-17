package centre.elife.fronted_autoconfiance.Models

data class Employee(
    val id: String = "",
    val name: String,
    val lastName: String,
    val address: String,
    val email: String,
    val password: String,
    val birthDate: String,
    val poste: String,
    val photo: ByteArray?
)
