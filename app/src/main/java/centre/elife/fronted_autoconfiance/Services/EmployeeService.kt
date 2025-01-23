package centre.elife.fronted_autoconfiance.Services

import android.media.session.MediaSession.Token
import centre.elife.fronted_autoconfiance.ApiInit.ApiInit
import centre.elife.fronted_autoconfiance.Repositories.EmployeeRepository
import centre.elife.fronted_autoconfiance.data.Dto.UpdateEmployeeAccountDto
import centre.elife.fronted_autoconfiance.data.models.UpdateProfileResponse
import retrofit2.Response

object EmployeeService {
    private val api: EmployeeRepository = ApiInit.retrofit.create(EmployeeRepository::class.java)
    suspend fun updateEmployeeAccount(token: String,email: String,name: String, lastName: String, address: String, birthDate: String): Response<UpdateProfileResponse> {
        val response = api.updateEmployeeAccount(token, UpdateEmployeeAccountDto(email, name, lastName, address, birthDate))
        return response
    }
}