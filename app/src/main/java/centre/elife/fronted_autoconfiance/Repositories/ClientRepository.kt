package centre.elife.fronted_autoconfiance.Repositories

import centre.elife.fronted_autoconfiance.data.Dto.LoginDto
import centre.elife.fronted_autoconfiance.data.Dto.SignupDto
import centre.elife.fronted_autoconfiance.data.models.LoginResponseModel
import centre.elife.fronted_autoconfiance.data.models.SignupResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ClientRepository {

    @POST("/api/authentication/login")
    suspend fun login(@Body loginData: LoginDto): Response<LoginResponseModel>

    @POST("/api/authentication/signup")
    suspend fun signup(@Body SignupData: SignupDto): Response<SignupResponseModel>

}