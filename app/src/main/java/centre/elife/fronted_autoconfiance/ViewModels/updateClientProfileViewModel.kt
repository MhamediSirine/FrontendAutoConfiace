package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import kotlinx.coroutines.launch

class updateClientProfileViewModel :ViewModel() {

    val success = MutableLiveData<Boolean>(false)


    fun updateClientProfile(email: String,name: String, lastName: String,number: String, address: String,token: String)
    {
        viewModelScope.launch {
            success.value = false

            val result = ClientService.updateClientProfile(email,name, lastName,number, address,token)
            if (result.isSuccessful) {
                success.value = true
            } else {
                success.value = false
            }

            }

    }

}