package centre.elife.fronted_autoconfiance.Services

import centre.elife.fronted_autoconfiance.ApiInit.ApiInit
import centre.elife.fronted_autoconfiance.Repositories.ClientRepository
import centre.elife.fronted_autoconfiance.data.Dto.DeleteAccountDto
import centre.elife.fronted_autoconfiance.data.Dto.LoginDto
import centre.elife.fronted_autoconfiance.data.Dto.ResetPasswordDto
import centre.elife.fronted_autoconfiance.data.Dto.SendEmailDto
import centre.elife.fronted_autoconfiance.data.Dto.SignupDto
import centre.elife.fronted_autoconfiance.data.Dto.UpdateClientProfileDto
import centre.elife.fronted_autoconfiance.data.models.DeleteProfileResponse
import centre.elife.fronted_autoconfiance.data.models.LoginResponseModel
import centre.elife.fronted_autoconfiance.data.models.ProfileDetailsResponse
import centre.elife.fronted_autoconfiance.data.models.ResetPasswordResponse
import centre.elife.fronted_autoconfiance.data.models.SignupResponseModel
import centre.elife.fronted_autoconfiance.data.models.UpdateProfileResponse
import centre.elife.fronted_autoconfiance.data.models.sendEmailResponse
import retrofit2.Response

object ClientService {
    private val api: ClientRepository = ApiInit.retrofit.create(ClientRepository::class.java)


    suspend fun login(email: String, password: String): Response<LoginResponseModel> {
        val loginData = LoginDto(email, password)
        val response= api.login(loginData)
        return response
    }
    suspend fun signup(name: String,lastName: String,address: String,email: String,password: String,number: String): Response<SignupResponseModel>{
        val SignupData = SignupDto(name, lastName, address, email, password,number)
        val response= api.signup(SignupData)
        return response
    }
    suspend fun SendEmail(email: String): Response<sendEmailResponse> {
        val SendEmailDto = SendEmailDto(email)
        val response = api.SendEmail(SendEmailDto)
        return response
    }
    suspend fun resetPassword(email: String, code: String, newPassword: String): Response<ResetPasswordResponse> {
        val resetPasswordDto = ResetPasswordDto(email, code, newPassword)
        val response = api.resetPassword(resetPasswordDto)
        return response

    }

    suspend fun getProfile(email: String, token: String): Response<ProfileDetailsResponse> {
        val response = api.getProfile(email,token)
        return response;

    }
    suspend fun deleteAccount(email: String, password: String, token: String): Response<DeleteProfileResponse> {
        val deleteAccountDto = DeleteAccountDto(email, password)
        val response = api.deleteAccount(deleteAccountDto, token)
        return response;
    }
    suspend fun updateClientProfile(email: String,name: String, lastName: String,number: String, address: String,token: String): Response<UpdateProfileResponse> {
        val response = api.updateClientAccount(UpdateClientProfileDto(email,name, lastName,number, address), token)
        return response;
    }





}