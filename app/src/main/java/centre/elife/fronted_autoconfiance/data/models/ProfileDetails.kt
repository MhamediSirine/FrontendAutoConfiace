package centre.elife.fronted_autoconfiance.data.models

data class ProfileDetails(
    val name: String,
    val lastName: String,
    val address: String,
    val email: String,
    val birthDate: String? = null, // Optional field for Employee
    val poste: String? = null, // Optional field for Employee
    val number: String? = null // Optional field for Client
)

