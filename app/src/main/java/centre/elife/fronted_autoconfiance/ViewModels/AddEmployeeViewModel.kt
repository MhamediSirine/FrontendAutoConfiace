package centre.elife.fronted_autoconfiance.ViewModels



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import centre.elife.fronted_autoconfiance.Services.AdminService
import centre.elife.fronted_autoconfiance.data.models.EmployeeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class AddEmployeeViewModel : ViewModel() {
    // LiveData for the API response
    private val _employeeResponse = MutableLiveData<Response<EmployeeResponse>?>(null)
    val employeeResponse: LiveData<Response<EmployeeResponse>?> get() = _employeeResponse


    private val _isEmployeeAdded = MutableStateFlow<Boolean?>(null)
    val isEmployeeAdded: StateFlow<Boolean?> = _isEmployeeAdded


    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage


    fun addEmployee(name: String,lastName: String,address: String,email: String,password: String,birthDate: String,poste: String
    ) {
        viewModelScope.launch {
            try {
                val response = AdminService.createEmployee(name, lastName, address, email, password, birthDate, poste)
                _employeeResponse.value = response

                if (response.isSuccessful) {
                    _isEmployeeAdded.value = true
                } else {
                    _isEmployeeAdded.value = false
                    _errorMessage.value = response.errorBody()?.string() ?: "Failed to add employee"
                }
            } catch (e: Exception) {
                _isEmployeeAdded.value = false
                _errorMessage.value = e.localizedMessage ?: "An unexpected error occurred"
            }
        }
    }
}
