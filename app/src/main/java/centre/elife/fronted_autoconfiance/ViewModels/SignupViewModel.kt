package centre.elife.fronted_autoconfiance.ViewModels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService
import centre.elife.fronted_autoconfiance.SignupRoute
import centre.elife.fronted_autoconfiance.data.models.SignupResponseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class SignupViewModel : ViewModel(){
    private val _signupResponse = MutableLiveData<Response<SignupResponseModel>?>(null)
    val signupResponse: LiveData<Response<SignupResponseModel>?> get() = _signupResponse

    private val _isSignupSuccessful = MutableStateFlow<Boolean?>(null)
    val isSignupSuccessful: StateFlow<Boolean?> = _isSignupSuccessful

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun signup(name: String, lastName: String, address: String, email: String, password: String, number: String) {
        viewModelScope.launch {
            try {
                val response = ClientService.signup(name, lastName, address, email, password,number)
                _signupResponse.value = response
                if (response.isSuccessful) {
                    _isSignupSuccessful.value = true
                } else {
                    _isSignupSuccessful.value = false
                    _errorMessage.value = response.errorBody()?.string() ?: "Signup failed"
                }
            } catch (e: Exception) {
                _isSignupSuccessful.value = false
                _errorMessage.value = e.localizedMessage ?: "An unexpected error occurred"
            }
        }
    }

}