package centre.elife.fronted_autoconfiance.data.models

data class PendingMeeting(
    val message: String,
    val data: List<Meeting>
)

data class Meeting (
    val id: Int,
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