package centre.elife.fronted_autoconfiance.Views.ClientProfile

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.ViewModels.EmployeeProfileViewModel
import centre.elife.fronted_autoconfiance.ViewModels.deleteAccountClientViewModel

@Composable
fun ClientProfileOption(icon: ImageVector, label: String, isLogout: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() } // Fix: Connect the onClick callback
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isLogout) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isLogout) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ClientProfileCard(viewModel: EmployeeProfileViewModel = EmployeeProfileViewModel()) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var profileEmail by remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(Unit) {

        val email = DataStoreManager.getEmail(context);
        val token = DataStoreManager.getToken(context);

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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ClientProfileOption(Icons.Default.AccountBox, name) { /* Handle Name Click */ }
            ClientProfileOption(Icons.Default.AccountBox, lastName) { /* Handle Last Name Click */ }
            ClientProfileOption(Icons.Default.Phone, number) { /* Handle Phone Number Click */ }
            ClientProfileOption(Icons.Default.LocationOn, address) { /* Handle Address Click */ }

            ClientProfileOption(
                icon = Icons.Default.Delete,
                label = "Delete Account",
                isLogout = true,
                onClick = { showDeleteDialog = true } // Open dialog on click
            )
        }
    }

    // Show the delete account dialog if triggered
    if (showDeleteDialog) {
        DeleteAccountDialog(onDismiss = { showDeleteDialog = false })
    }
}

@Composable
fun DeleteAccountDialog(onDismiss: () -> Unit,deleteAccountClientViewModel: deleteAccountClientViewModel= deleteAccountClientViewModel()) {

    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val context = LocalContext.current


    val userEmail by produceState(initialValue = "") {
        value = DataStoreManager.getEmail(context)
    }


    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Account") },
        text = {
            Column {
                Text("Are you sure you want to delete your account? This action cannot be undone.")
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    isError = errorMessage.isNotEmpty()
                )


            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (password.isNotEmpty())
                    {
                    deleteAccountClientViewModel.deleteAccount(userEmail, password,"")
                    deleteAccountClientViewModel.success.observeForever { success ->
                        if (!success) {
                           Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Account Deleted", Toast.LENGTH_SHORT).show()
                            onDismiss()
                        }
                }}}

            ) {
                Text("Delete", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun ClientProfileCardPreview() {
    ClientProfileCard()
}