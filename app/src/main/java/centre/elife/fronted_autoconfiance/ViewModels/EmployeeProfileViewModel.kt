package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import centre.elife.fronted_autoconfiance.data.Dto.EmployeeDto
import centre.elife.fronted_autoconfiance.data.models.ProfileDetailsResponse
import kotlinx.coroutines.launch

class EmployeeProfileViewModel:ViewModel() {

    val success = MutableLiveData<Boolean>(false)

    val profileDetails = MutableLiveData<ProfileDetailsResponse>()

    fun getProfile(email: String, token: String) {
        viewModelScope.launch {
            try {
                val result = ClientService.getProfile(email, token)
                if (result.isSuccessful) {
                    success.value = true
                    profileDetails.value = result.body()
                } else {
                    success.value = false
                }
                } catch (e: Exception) {
                success.value = false
            }
        }
    }
}