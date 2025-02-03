package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import kotlinx.coroutines.launch

class requestMeetingViewModel : ViewModel()  {
    val success = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String?>(null)
    fun requestMeeting(email: String, carType: String, carLicence: String, name: String, lastName: String, phoneNumber: String, hour: Int, minute: Int, day: Int, month: Int, year: Int, token: String){
        viewModelScope.launch {
            success.value = false
            val result = ClientService.requestMeeting(
                email,
                carType,
                carLicence,
                name,
                lastName,
                phoneNumber,
                hour,
                minute,
                day,
                month,
                year,
                token
            )
            if (result.isSuccessful) {
                success.value = true
            } else {
                success.value = false
                error.value = result.errorBody()?.string()
            }

        }
    }
}