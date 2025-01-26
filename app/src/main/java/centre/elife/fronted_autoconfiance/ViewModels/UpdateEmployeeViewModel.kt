package centre.elife.fronted_autoconfiance.ViewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.AdminService
import kotlinx.coroutines.launch


class UpdateEmployeeViewModel: ViewModel() {
    val success = MutableLiveData<Boolean>(false)


    fun updateEmployee(email: String,name: String, lastName: String, address: String, birthDate: String, poste: String,token: String)
    {
        viewModelScope.launch {
            success.value = false

            val result = AdminService.updateEmployee( email,name, lastName, address, birthDate, poste,token)
            if (result.isSuccessful) {
                success.value = true
            } else {
                success.value = false
            }
        }
    }
}