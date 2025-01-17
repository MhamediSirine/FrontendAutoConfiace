package centre.elife.fronted_autoconfiance.ViewModels

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager

import centre.elife.fronted_autoconfiance.Services.ClientService
import kotlinx.coroutines.launch

class SendEmailViewModel : ViewModel(){

    val loading = MutableLiveData<Boolean>(false)
    val responseCode = MutableLiveData<Int>()
    val success = MutableLiveData<Boolean?>(null)

    fun sendEmail(context: Context, email: String) {

        viewModelScope.launch {

            loading.value = true

            val response = ClientService.SendEmail(email)
            responseCode.value = response.code()
            if (response.isSuccessful) {
                DataStoreManager.setEmail(context, email)
                success.value = true

            } else {
                success.value = false
            }
            loading.value = false

        }
    }
}