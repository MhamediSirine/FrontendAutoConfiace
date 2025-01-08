package centre.elife.fronted_autoconfiance.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import centre.elife.fronted_autoconfiance.Services.DataStoreManager
import centre.elife.fronted_autoconfiance.data.models.LoginResponseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val _loginResponse = MutableStateFlow<Response<LoginResponseModel>?>(null)
    val loginResponse: StateFlow<Response<LoginResponseModel>?> get() = _loginResponse

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            val response = ClientService.login(email, password)
            _loginResponse.value = response
            if (response.isSuccessful && response.body()?.data != null) {
                DataStoreManager.setEmail(context, email)
                DataStoreManager.setLoggedIn(context, true)
                _isLoggedIn.value = true
            } else {
                _isLoggedIn.value = false
            }
        }
    }

    fun logout(context: Context) {
        viewModelScope.launch {
            DataStoreManager.logout(context)
            _isLoggedIn.value = false
        }
    }

    fun checkLoginStatus(context: Context) {
        viewModelScope.launch {
            _isLoggedIn.value = DataStoreManager.isLoggedIn(context)
        }
    }
}
