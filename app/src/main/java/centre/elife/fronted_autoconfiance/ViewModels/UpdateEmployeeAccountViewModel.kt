package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.EmployeeService
import kotlinx.coroutines.launch

class UpdateEmployeeAccountViewModel : ViewModel() {
    var success = MutableLiveData<Boolean>(false)

    fun updateEmployeeAccount(token: String, email: String, name: String, lastName: String, address: String, birthDate: String) {
        viewModelScope.launch {
            success.value = false
            val response = EmployeeService.updateEmployeeAccount(token, email, name, lastName, address, birthDate)
            if (response.isSuccessful) {
                success.value = true
            } else {
                success.value = false
            }
        }
    }
}