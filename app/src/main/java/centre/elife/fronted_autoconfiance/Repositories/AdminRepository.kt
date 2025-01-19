package centre.elife.fronted_autoconfiance.Repositories

import centre.elife.fronted_autoconfiance.data.Dto.EmployeeDto
import centre.elife.fronted_autoconfiance.data.Dto.ModifyDto
import centre.elife.fronted_autoconfiance.data.models.EmployeeResponse
import centre.elife.fronted_autoconfiance.data.models.ListProfileResponse
import centre.elife.fronted_autoconfiance.data.models.ProfileDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface AdminRepository{
    @POST("/api/admin/create-employee-account")
    suspend fun createEmployee(@Body employeeData: EmployeeDto): Response<EmployeeResponse>
    @GET("/api/admin/employees-list ")
    suspend fun getEmployees(@Header("Authorization") token: String):Response<ListProfileResponse>
    @PUT("/api/admin/update-employee")
    suspend fun updateEmployee(@Body employeeData: ModifyDto,@Header("Authorization") token: String):Response<EmployeeResponse>


}