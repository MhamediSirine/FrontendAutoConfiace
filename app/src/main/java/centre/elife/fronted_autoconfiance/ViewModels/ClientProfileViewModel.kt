package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import centre.elife.fronted_autoconfiance.data.Dto.UpdateClientProfileDto
import kotlinx.coroutines.launch

class ClientProfileViewModel : ViewModel() {
    val success = MutableLiveData<Boolean>(false)

    fun updateClientProfile(profileData: UpdateClientProfileDto, token: String) {
        viewModelScope.launch {
           try {
               val result = ClientService.updateClientProfile(profileData, token)

               if (result.isSuccessful) {
                   success.value = true
               } else {
                   success.value = false
               }
           } catch (e: Exception) {
               success.value = false
           }
        }
    }

}