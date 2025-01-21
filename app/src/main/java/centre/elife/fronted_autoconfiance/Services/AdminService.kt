package centre.elife.fronted_autoconfiance.Services

import centre.elife.fronted_autoconfiance.ApiInit.ApiInit
import centre.elife.fronted_autoconfiance.Repositories.AdminRepository
import centre.elife.fronted_autoconfiance.data.Dto.EmployeeDto
import centre.elife.fronted_autoconfiance.data.Dto.ModifyDto
import centre.elife.fronted_autoconfiance.data.Dto.UpdateAdminDto
import centre.elife.fronted_autoconfiance.data.models.EmployeeResponse
import centre.elife.fronted_autoconfiance.data.models.ListProfileResponse
import centre.elife.fronted_autoconfiance.data.models.UpdateProfileResponse
import retrofit2.Response

object AdminService {
    private val api: AdminRepository = ApiInit.retrofit.create(AdminRepository::class.java)

    suspend fun createEmployee(name: String, lastName: String, address: String, email: String, password: String, birthDate: String, poste: String): Response<EmployeeResponse> {
        val employeeData = EmployeeDto(name, lastName, address, email, password, birthDate, poste)
        val response = api.createEmployee(employeeData)
        return response

    }
    suspend fun getEmployees(token: String): Response<ListProfileResponse> {
       val list=api.getEmployees(token)
        return list
    }
    suspend fun updateEmployee(email: String,name: String, lastName: String, address: String, birthDate: String, post: String, token: String): Response<UpdateProfileResponse> {
        val employeeData = ModifyDto(email,name, lastName, address, birthDate, post)
        val response = api.updateEmployee(employeeData,token)
        return response
    }
    suspend fun updateAccount(email: String,name: String, lastName: String, address: String, token: String): Response<UpdateProfileResponse> {
        val employeeData = UpdateAdminDto(email,name, lastName, address)
        val response = api.updateAccount(employeeData,token)
        return response

    }

}