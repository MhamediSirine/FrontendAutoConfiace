package centre.elife.fronted_autoconfiance.data.Dto

data class UpdateClientProfileDto(
    val name: String,
    val lastname: String,
    val phoneNumber: String,
    val address: String,
    val password: String,
    val email: String
)
