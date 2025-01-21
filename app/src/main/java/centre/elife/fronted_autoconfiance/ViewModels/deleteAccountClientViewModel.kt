package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import kotlinx.coroutines.launch

class deleteAccountClientViewModel : ViewModel() {
    val success = MutableLiveData<Boolean>(false)
    val loading = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String?>(null)
    val responseCode = MutableLiveData<Int?>(null)

    fun deleteAccount(email: String, password: String,token: String) {

        viewModelScope.launch {
            loading.value = true
            try {
                val result = ClientService.deleteAccount(email, password,token)
                if (result.isSuccessful) {
                    errorMessage.value = null
                    success.value = true
                } else {
                    errorMessage.value = "Invalid email or password"
                    success.value = false
                }
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "An unexpected error occurred."
                responseCode.value = 500
            }

        }

    }



}