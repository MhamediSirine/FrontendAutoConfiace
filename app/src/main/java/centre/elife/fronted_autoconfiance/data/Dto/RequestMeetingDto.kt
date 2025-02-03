package centre.elife.fronted_autoconfiance.data.Dto

data class RequestMeetingDto(
val email: String,
val carType: String,
val carLicence: String,
val name: String,
val lastName: String,
val phoneNumber: String,
val hour: Int,
val minute: Int,
val day: Int,
val month: Int,
val year: Int
)
