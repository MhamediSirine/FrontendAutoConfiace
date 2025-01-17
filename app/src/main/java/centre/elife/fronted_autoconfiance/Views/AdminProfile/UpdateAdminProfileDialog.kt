package centre.elife.fronted_autoconfiance.Views.AdminProfile


import androidx.compose.ui.unit.dp


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun UpdateAdminProfileDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Profile") },
        text = {
            Column {
                var name by remember { mutableStateOf("") }
                var lastName by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var address by remember { mutableStateOf("") }


                TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("last Name") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                Spacer(modifier = Modifier.height(8.dp))

                TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })
                Spacer(modifier = Modifier.height(8.dp))





            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}