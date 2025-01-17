package centre.elife.fronted_autoconfiance.data.models

data class ProfileDetailsResponse(
    val message: String, // Corresponds to the message from ApiResponse
    val data: ProfileDetails? = null // Aligns with the ProfileDetails object from the backend
)