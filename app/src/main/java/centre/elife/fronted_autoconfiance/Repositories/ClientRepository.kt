package centre.elife.fronted_autoconfiance.Repositories

import centre.elife.fronted_autoconfiance.data.Dto.LoginDto
import centre.elife.fronted_autoconfiance.data.Dto.ResetPasswordDto
import centre.elife.fronted_autoconfiance.data.Dto.SendEmailDto
import centre.elife.fronted_autoconfiance.data.Dto.SignupDto
import centre.elife.fronted_autoconfiance.data.models.LoginResponseModel
import centre.elife.fronted_autoconfiance.data.models.ResetPasswordResponse
import centre.elife.fronted_autoconfiance.data.models.SignupResponseModel
import centre.elife.fronted_autoconfiance.data.models.sendEmailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientRepository {

    @POST("/api/authentication/login")
    suspend fun login(@Body loginData: LoginDto): Response<LoginResponseModel>

    @POST("/api/authentication/signup")
    suspend fun signup(@Body SignupData: SignupDto): Response<SignupResponseModel>

    @POST("/api/authentication/send-reset-code")
    suspend fun SendEmail(@Body SendEmailDto: SendEmailDto): Response<sendEmailResponse>

@POST("/api/authentication/reset-password")
suspend fun resetPassword(@Body resetPasswordDto: ResetPasswordDto): Response<ResetPasswordResponse>


}