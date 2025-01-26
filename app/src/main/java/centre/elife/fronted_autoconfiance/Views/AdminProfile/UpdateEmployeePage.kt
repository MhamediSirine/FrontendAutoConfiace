package centre.elife.fronted_autoconfiance.Views.AdminProfile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import centre.elife.fronted_autoconfiance.AddEmployeeRoute
import centre.elife.fronted_autoconfiance.AdminRoute
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.DetailsRoute
import centre.elife.fronted_autoconfiance.ListEmployeeRoute
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.ServicesRoute
import centre.elife.fronted_autoconfiance.ViewModels.EmployeeProfileViewModel
import centre.elife.fronted_autoconfiance.ViewModels.UpdateEmployeeViewModel
import centre.elife.fronted_autoconfiance.ui.theme.background
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateEmployeePage(navController: NavHostController,viewModel: EmployeeProfileViewModel = EmployeeProfileViewModel(),UpdateEmployeeViewModel: UpdateEmployeeViewModel = UpdateEmployeeViewModel()) {

    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var profileEmail by remember { mutableStateOf("") }
    var post by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {

        val email = DataStoreManager.getEmailToUpdate(context);
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
    val postOptions = listOf("RH", "Finance", "Mechanique")
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Menu",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )


                val options = listOf("Sevices", "Profile","Employee Management", "Add Employee", "Logout", "About")
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch { drawerState.close()
                                    when (option) {
                                        "Services" -> navController.navigate(ServicesRoute)
                                        "Profile" -> navController.navigate(AdminRoute)
                                        "Employee Management" -> navController.navigate(
                                            ListEmployeeRoute
                                        )
                                        "Add Employee" -> navController.navigate(AddEmployeeRoute)
                                        "Logout" -> navController.navigate(LoginRoute)
                                        "About" -> navController.navigate(DetailsRoute) }
                                    println("Selected Option: $option")
                                }}
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = when (option) {
                                "Services" -> Icons.Default.Home
                                "Profile" -> Icons.Default.Person
                                "Employee Management" -> Icons.Default.Settings
                                "Add Employee" -> Icons.Default.Add
                                "Logout" -> Icons.Default.ExitToApp
                                "About" -> Icons.Default.Info
                                else -> Icons.Default.Refresh
                            },
                            contentDescription = option,
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = option, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(" Admin Profile") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Text(
                text = "Update Employee",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 30.sp,
                color = secondary,
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(name, color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Name Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Last Name Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Address Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = post,
                onValueChange = { post = it },
                label = { Text(post, color = Color.Gray) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = {

                    UpdateEmployeeViewModel.updateEmployee(profileEmail,name,lastName,address,birthDate,post,"")
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
                colors = ButtonDefaults.buttonColors(containerColor = background),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = "Save Changes", color = Color.White)
            }
        }
    }
}}

@Preview
@Composable
fun UpdateEmployeePagePreview() {
    UpdateEmployeePage(navController = rememberNavController())
}
