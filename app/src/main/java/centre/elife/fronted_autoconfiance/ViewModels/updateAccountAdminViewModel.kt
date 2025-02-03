package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.AdminService
import kotlinx.coroutines.launch

class updateAccountAdminViewModel : ViewModel() {
    val success = MutableLiveData<Boolean>(false)
    fun updateAccount(email: String,name: String, lastName: String, address: String,token: String)
    {
        viewModelScope.launch {
            success.value = false

            val result = AdminService.updateAccount( email,name, lastName, address,token)
            if (result.isSuccessful) {
                success.value = true
            } else {
                success.value = false
            }
        }
    }
}

