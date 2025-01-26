package centre.elife.fronted_autoconfiance.Repositories

import centre.elife.fronted_autoconfiance.data.Dto.HandleMeeting
import centre.elife.fronted_autoconfiance.data.Dto.UpdateEmployeeAccountDto
import centre.elife.fronted_autoconfiance.data.models.PendingMeeting
import centre.elife.fronted_autoconfiance.data.models.UpdateProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface EmployeeRepository {
    @PUT("/api/employee/update-account")
    suspend fun updateEmployeeAccount(
        @Header("Authorization") token: String,
        @Body employeeData: UpdateEmployeeAccountDto
    ): Response<UpdateProfileResponse>

    @GET("/api/employee/pending-meetings")
    suspend fun getPendingMeetings(): Response<PendingMeeting>

    @POST("/api/employee/handle-meeting")
    suspend fun handleMeeting(@Body meetingData: HandleMeeting): Response<UpdateProfileResponse>

    @GET("/api/employee/accepted-meetings")
    suspend fun fetchAcceptedMeetings(): Response<PendingMeeting>

}