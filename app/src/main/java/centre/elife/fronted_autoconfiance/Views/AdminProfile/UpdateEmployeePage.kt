package centre.elife.fronted_autoconfiance.Views.AdminProfile

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.ui.theme.secondary
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.tooling.preview.Preview
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.ViewModels.EmployeeProfileViewModel
import centre.elife.fronted_autoconfiance.ViewModels.UpdateEmployeeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEmployeePage(navController: NavHostController,viewModel: EmployeeProfileViewModel = EmployeeProfileViewModel(),UpdateEmployeeViewModel: UpdateEmployeeViewModel = UpdateEmployeeViewModel()) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var profileEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var post by remember { mutableStateOf("") }

    var birthDate by remember { mutableStateOf("") }

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
                    birthDate = details.data?.birthDate ?: ""
                    post = details.data?.poste ?: ""
                    profileEmail = email
                }
            }
        }

    }

    var expanded by remember { mutableStateOf(false) }


    val postOptions = listOf("RH", "Finance", "Mechanique") // Post options

    Scaffold(
        topBar = { TopAppBar(title = { Text("Modify Employer") }) }
    ) { paddingValues -> // Correct usage of padding
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Use paddingValues here
                .padding(20.dp) // Additional padding
        ) {
            Text(
                text = "Modify Employee",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 30.sp,
                color = secondary,
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Name Field
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(name, color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Last Name Field
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Last Name Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Address Field
            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Address Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))



            // Post Field (Dropdown)
            TextField(
                value = post,
                onValueChange = { post = it },
                label = { Text("Post", color = Color.Gray) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = {
                    UpdateEmployeeViewModel.updateEmployee(name,lastName,address,birthDate,post,"")
                    UpdateEmployeeViewModel.success.observeForever { success ->
                        if (!success) {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(context, "Changes Saved", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        }
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = "Save Changes", color = Color.White)
            }
        }
    }
}

@Preview
@Composable
fun UpdateEmployeePagePreview() {
    UpdateEmployeePage(navController = rememberNavController())
}
