package centre.elife.fronted_autoconfiance.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import centre.elife.fronted_autoconfiance.data.models.LoginResponseModel
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val response = MutableLiveData<Response<LoginResponseModel>>()
    val loading = MutableLiveData<Boolean>(false)
    val responseCode = MutableLiveData<Int>()
    val errorMessage = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean?>(null)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loading.value = true
            try {
                val result = ClientService.login(email, password)
                response.value = result
                responseCode.value = result.code()

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
            } finally {
                loading.value = false
            }
        }
    }
}
