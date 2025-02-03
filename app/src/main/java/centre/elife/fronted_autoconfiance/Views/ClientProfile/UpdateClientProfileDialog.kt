package centre.elife.fronted_autoconfiance.Views.ClientProfile

import android.widget.Toast
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.ViewModels.EmployeeProfileViewModel
import centre.elife.fronted_autoconfiance.ViewModels.updateClientProfileViewModel

@Composable
fun UpdateClientProfileDialog(onDismiss: () -> Unit,viewModel: EmployeeProfileViewModel = EmployeeProfileViewModel(),updateClientProfileViewModel: updateClientProfileViewModel = updateClientProfileViewModel()) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var profileEmail by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var emaill by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {

        val email = DataStoreManager.getEmail(context);
        val token = DataStoreManager.getToken(context);
        emaill=email
        viewModel.getProfile(email, token)

        viewModel.success.observeForever { success ->
            if (!success) {

            } else {
                viewModel.profileDetails.observeForever { details ->
                    name = details.data?.name ?: ""
                    lastName = details.data?.lastName ?: ""
                    address = details.data?.address ?: ""
                    number = details.data?.number ?: ""
                    profileEmail = email
                }
            }
        }

    }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Profile") },
        text = {
            Column {


                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("last Name") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = number, onValueChange = { number = it }, label = { Text("Phone Number") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
                Spacer(modifier = Modifier.height(8.dp))


            }
        },
        confirmButton = {
            TextButton(onClick = {
              updateClientProfileViewModel.updateClientProfile(profileEmail,name,lastName,number,address,"")
                updateClientProfileViewModel.success.observeForever { success ->
                    if (!success) {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show()
                        onDismiss()
            }}}
            ) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}