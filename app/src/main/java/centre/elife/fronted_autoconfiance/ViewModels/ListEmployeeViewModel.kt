package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.AdminService

import centre.elife.fronted_autoconfiance.data.models.ProfileDetails
import kotlinx.coroutines.launch

class ListEmployeeViewModel:ViewModel() {
    val success = MutableLiveData<Boolean>(false)
    private val _employees = MutableLiveData<List<ProfileDetails>>()
    val employees: LiveData<List<ProfileDetails>> = _employees
    fun getEmployees(token: String) {
    viewModelScope.launch {
        try {
            val response = AdminService.getEmployees(token)
            if (response.isSuccessful) {
                success.value = true
                _employees.value = response.body()?.data ?: emptyList()
            } else {
                success.value = false
            }
        } catch (e: Exception) {
            success.value = false

            }

        }

    }
}


