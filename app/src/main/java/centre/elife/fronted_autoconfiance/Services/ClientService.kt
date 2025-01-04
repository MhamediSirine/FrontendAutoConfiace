package centre.elife.fronted_autoconfiance.Services

import centre.elife.fronted_autoconfiance.data.dto.LoginDto
import centre.elife.fronted_autoconfiance.data.dto.SignupDto
import centre.elife.fronted_autoconfiance.data.models.LoginResponseModel
import centre.elife.fronted_autoconfiance.data.models.SignupResponseModel
import isetb.elife.projectorenda.data.ApiInit
import retrofit2.Response

object ClientService {

    private val api: ClientRepository = ApiInit.retrofit.create(ClientRepository::class.java)


    suspend fun login(email: String, password: String): Response<LoginResponseModel> {
        val loginData = LoginDto(email, password)
        val response= api.login(loginData)
        return response

    }

    suspend fun signup(name: String, lastName: String, address: String, email: String, password: String): Response<SignupResponseModel>{
        val SignupData = SignupDto(name, lastName, address, email, password)
        val response= api.signup(SignupData)
        return response
    }
}