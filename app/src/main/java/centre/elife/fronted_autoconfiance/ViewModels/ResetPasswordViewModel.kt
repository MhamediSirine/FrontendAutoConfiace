package centre.elife.fronted_autoconfiance.ViewModels


import centre.elife.fronted_autoconfiance.data.Dto.ResetPasswordDto
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.ClientService

import kotlinx.coroutines.launch

class ResetPasswordViewModel : ViewModel() {

    val loading = MutableLiveData<Boolean>(false)
    val responseCode = MutableLiveData<Int>()
    val errorMessage = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean?>(null);

    fun resetPassword(code: String, newPassword: String) {
        viewModelScope.launch {

            loading.value = true
            try {
                val resetPasswordDto = ResetPasswordDto(code, newPassword)
                val response = ClientService.resetPassword(code, newPassword)

                if (response.isSuccessful) {
                    success.value = true;
                    responseCode.value = response.code()
                } else {
                    success.value = false;
                    errorMessage.value = response.errorBody()?.string() ?: "Unknown error"
                }
            } catch (e: Exception) {
                errorMessage.value = e.message ?: "An error occurred"
            } finally {
                loading.value = false
            }
        }
    }
}
