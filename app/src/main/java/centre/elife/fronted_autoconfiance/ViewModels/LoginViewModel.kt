package centre.elife.fronted_autoconfiance.ViewModels

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.Services.ClientService
import centre.elife.fronted_autoconfiance.data.models.LoginResponseModel
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    val role = MutableLiveData<String>("")
    val loading = MutableLiveData<Boolean>(false)
    val responseCode = MutableLiveData<Int>()
    val errorMessage = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean?>(null)

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            loading.value = true
            try {
                val result = ClientService.login(email, password)

                if (result.isSuccessful) {
                    errorMessage.value = null
                    success.value = true

                    val token = result.body()?.data?.token;
                    val email = result.body()?.data?.email;

                    // 7ot token fel shared preferences
                    if (token != null) {
                        DataStoreManager.setToken(context, token)
                    }

                    if (email != null) {
                        DataStoreManager.setEmail(context, email)
                    }

                    role.value = result.body()?.data?.role

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
