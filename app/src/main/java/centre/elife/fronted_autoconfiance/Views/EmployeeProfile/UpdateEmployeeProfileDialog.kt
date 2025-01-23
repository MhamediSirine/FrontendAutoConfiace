package centre.elife.fronted_autoconfiance.Views.EmployeeProfile


import android.widget.Toast
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.ViewModels.EmployeeProfileViewModel
import centre.elife.fronted_autoconfiance.ViewModels.UpdateEmployeeAccountViewModel

@Composable
fun UpdateEmployeeProfileDialog(
    onDismiss: () -> Unit,
    viewModel: UpdateEmployeeAccountViewModel= UpdateEmployeeAccountViewModel(),
    viewModelProfil:EmployeeProfileViewModel= EmployeeProfileViewModel()) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var emailL by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var context = LocalContext.current

    LaunchedEffect(Unit) {
        var email = DataStoreManager.getEmail(context)
        var token = DataStoreManager.getToken(context)
        viewModelProfil.getProfile(email, token)
        viewModelProfil.success.observeForever {
            if (!it) {
            } else {
                viewModelProfil.profileDetails.observeForever {
                    name = it.data?.name?:""
                    lastName = it.data?.lastName?:""
                    address = it.data?.address?:""
                    birthDate = it.data?.birthDate?:""
                    emailL = email

                }
            }
        }
    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Profile") },
        text = {
            Column {



                TextField(value = name, onValueChange = { name = it }, label = { Text(name) })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("last Name") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = birthDate, onValueChange = { birthDate = it }, label = { Text("Birth Date") })
                Spacer(modifier = Modifier.height(8.dp))


            }
        },
        confirmButton = {
            TextButton(onClick = {
                viewModel.updateEmployeeAccount(
                    "",
                    emailL,
                    name,
                    lastName,
                    address,
                    birthDate
                )
                viewModel.success.observeForever {
                    if (it) {
                        Toast.makeText(context, "Profile Updated", Toast.LENGTH_SHORT).show()
                        onDismiss()
                    } else {
                        Toast.makeText(context, "Profile Not Updated", Toast.LENGTH_SHORT).show()
                    }
                }

            }) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )}
