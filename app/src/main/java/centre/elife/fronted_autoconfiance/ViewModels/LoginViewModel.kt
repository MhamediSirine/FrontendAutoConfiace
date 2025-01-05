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
    val loading = MutableLiveData<Boolean>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loading.value = true
            try {
                val result = ClientService.login(email, password)
                response.value = result
            } catch (e: Exception) {
                response.value = Response.error(
                    500,
                    okhttp3.ResponseBody.create(
                        okhttp3.MediaType.parse("text/plain"),
                        "Something went wrong."
                    )
                )
            } finally {
                loading.value = false
            }
        }
    }
}
