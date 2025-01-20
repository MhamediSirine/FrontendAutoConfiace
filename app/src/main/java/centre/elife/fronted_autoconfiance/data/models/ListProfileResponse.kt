package centre.elife.fronted_autoconfiance.data.models

data class ListProfileResponse(
    val message: String, // Corresponds to the message from ApiResponse
    val data: List<ProfileDetails>? = null)
