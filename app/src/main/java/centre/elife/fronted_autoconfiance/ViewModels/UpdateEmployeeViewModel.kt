package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.AdminService
import kotlinx.coroutines.launch

class UpdateEmployeeViewModel: ViewModel() {
    val success = MutableLiveData<Boolean>(false)


    fun updateEmployee(name: String, lastName: String, address: String, birthDate: String, poste: String,token: String)
    {
        viewModelScope.launch {
            val result = AdminService.updateEmployee( name, lastName, address, birthDate, poste,token)
            if (result.isSuccessful) {
                success.value = true


            } else {
                success.value = false
            }

        }
    }
}