package centre.elife.fronted_autoconfiance.Views.EmployeeProfile


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun EmployeeProfileOption(icon: ImageVector, label: String, isLogout: Boolean = false, onClick: () -> Unit) {
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
fun EmployeeProfileCard() {
    var showDeleteDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            EmployeeProfileOption(Icons.Default.AccountBox, "Name") { /* Handle Name Click */ }
            EmployeeProfileOption(Icons.Default.AccountBox, "Last Name") { /* Handle Last Name Click */ }
            EmployeeProfileOption(Icons.Default.Email, "Email") { /* Handle Email Click */ }
            EmployeeProfileOption(Icons.Default.LocationOn, "Address") { /* Handle Address Click */ }
            EmployeeProfileOption(Icons.Default.DateRange, "Birth Date") { /* Handle Birth Date Click */ }
            EmployeeProfileOption(Icons.Default.Phone, "Post") { /* Handle Phone Number Click */ }


            EmployeeProfileOption(
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
fun DeleteAccountDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Account") },
        text = { Text("Are you sure you want to delete your account? This action cannot be undone.") },
        confirmButton = {
            TextButton(onClick = {
                // TODO: Add account deletion logic here
                onDismiss()
            }) {
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
fun EmployeeProfileCardPreview() {
   EmployeeProfileCard()
}