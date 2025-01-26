package centre.elife.fronted_autoconfiance.Views.AdminProfile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.AddEmployeeRoute
import centre.elife.fronted_autoconfiance.AdminRoute
import centre.elife.fronted_autoconfiance.DetailsRoute
import centre.elife.fronted_autoconfiance.HomePageRoute
import centre.elife.fronted_autoconfiance.ListEmployeeRoute
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.ProfileAdminRoute
import centre.elife.fronted_autoconfiance.ServicesRoute
import centre.elife.fronted_autoconfiance.ViewModels.AddEmployeeViewModel
import centre.elife.fronted_autoconfiance.Views.validateEmail
import centre.elife.fronted_autoconfiance.ui.theme.background
import centre.elife.fronted_autoconfiance.ui.theme.secondary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployee(navController: NavHostController, AddEmployeeViewModel: AddEmployeeViewModel = AddEmployeeViewModel()) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var post by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
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

                val options = listOf("Details", "Profile","Employee Management","Add Employee", "Logout", "About")
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch { drawerState.close()
                                    when (option) {
                                        "Services" -> navController.navigate(ServicesRoute)
                                        "Profile" -> navController.navigate(ProfileAdminRoute)
                                        "Employee Management" -> navController.navigate(ListEmployeeRoute)
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
                    title = { Text("Profile") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Menu")
                        }
                    }
                )
            }
        )  { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {
            Text(
                text = "Add Employee",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 30.sp,
                color = background,
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name", color = Color.Gray) },
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
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = { Text("Birth Date (YYYY-MM-DD)", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = "Birth Date Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            // Dropdown Menu with Placeholder for Post
            Column {
                Text(
                    text = if (post.isEmpty()) "Select Post" else post, // Placeholder logic
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true }
                        .background(Color.LightGray, RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    color = if (post.isEmpty()) Color.Gray else Color.Black // Placeholder text in gray
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    postOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                post = option
                                expanded = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.isNotBlank() && lastName.isNotBlank() && address.isNotBlank() &&
                        email.isNotBlank() && password.isNotBlank() && birthDate.isNotBlank() && post.isNotBlank()
                    ) {
                        if (!validateEmail(email)) {
                            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        if (password.length < 8) {
                            Toast.makeText(context, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        AddEmployeeViewModel.addEmployee(name, lastName, address, email, password, birthDate, post)
                        Toast.makeText(context, "Employee Added Successfully", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "All fields are required.", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = background),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = "Save", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}}



@Preview
@Composable
fun AddEmployeePagePreview() {
    AddEmployee(navController = rememberNavController())
}
