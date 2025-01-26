package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.EmployeeService
import centre.elife.fronted_autoconfiance.data.models.Meeting
import kotlinx.coroutines.launch

class MeetingViewModel : ViewModel() {

    val pendingMeetings = MutableLiveData<List<Meeting>>()
    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
    val acceptedMeetings = MutableLiveData<List<Meeting>>()

    fun fetchPendingMeetings() {
        viewModelScope.launch {
            val response = EmployeeService.fetchPendingMeetings()

            if (response.isSuccessful) {
                pendingMeetings.value = response.body()?.data;
            } else {
                error.value = "Error fetching pending employees"
            }
        }
    }

    fun handleMeeting(meetingId: Int, accepted: Boolean) {
        viewModelScope.launch {
            val response = EmployeeService.handleMeeting(meetingId, accepted)

            success.value = response.isSuccessful
        }
    }

    fun fetchAcceptedMeetings() {
        viewModelScope.launch {
            val response = EmployeeService.fetchAcceptedMeetings()
            acceptedMeetings.value = response.body()?.data

        }
    }

}