package centre.elife.fronted_autoconfiance.data.Dto

data class ResetPasswordDto(
    val code: String,
    val newPassword: String,
)
