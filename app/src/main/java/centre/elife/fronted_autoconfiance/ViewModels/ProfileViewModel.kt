package centre.elife.fronted_autoconfiance.ViewModels

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.dataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.Services.ClientService
import centre.elife.fronted_autoconfiance.data.models.ProfileDetailsResponse
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private val _profileDetails = MutableLiveData<ProfileDetailsResponse?>()
    val profileDetails: LiveData<ProfileDetailsResponse?> get() = _profileDetails

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    /**
     * Fetches the profile details of the logged-in user.
     * @param email The email address of the user.
     * @param token The authorization token.
     */

    fun fetchProfile(email: String, token: String) {

        viewModelScope.launch {
            _loading.value = true
            try {

                val response: Response<ProfileDetailsResponse> = ClientService.getProfile(email, token)
                if (response.isSuccessful) {
                    _profileDetails.value = response.body()
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = response.errorBody()?.string() ?: "Failed to fetch profile data."
                    _profileDetails.value = null
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "An unexpected error occurred."
                _profileDetails.value = null
            } finally {
                _loading.value = false
            }
        }
    }
}
