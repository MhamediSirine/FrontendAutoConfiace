package centre.elife.fronted_autoconfiance.Services

import centre.elife.fronted_autoconfiance.ApiInit.ApiInit
import centre.elife.fronted_autoconfiance.Repositories.AdminRepository
import centre.elife.fronted_autoconfiance.Repositories.ClientRepository
import centre.elife.fronted_autoconfiance.data.Dto.EmployeeDto
import centre.elife.fronted_autoconfiance.data.Dto.ModifyDto
import centre.elife.fronted_autoconfiance.data.models.EmployeeResponse
import centre.elife.fronted_autoconfiance.data.models.ListProfileResponse
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
    suspend fun updateEmployee( name: String, lastName: String,  address: String , birthDate: String, poste: String,token: String): Response<EmployeeResponse> {
        val employeeData = ModifyDto(name, lastName, address, birthDate, poste)
        val response = api.updateEmployee(employeeData,token)
        return response
    }

}