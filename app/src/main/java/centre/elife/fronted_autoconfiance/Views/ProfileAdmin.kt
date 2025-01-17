package centre.elife.fronted_autoconfiance.Views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.ViewModels.ProfileViewModel
import centre.elife.fronted_autoconfiance.data.models.AdminData


import centre.elife.fronted_autoconfiance.ui.theme.primary

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileAdmin(navController: NavHostController,profileViewModel: ProfileViewModel = viewModel()) {
    val context = LocalContext.current
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false)} // State to control the popup dialog

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

                // Sidebar options
                val options = listOf("Home", "Profile", "Gestion des Employers", "Gestion des Clients", "Logout", "About")
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                when (option) {
                                    "Logout" -> {
                                        navController.navigate(LoginRoute)
                                    }
                                    else -> {
                                        scope.launch { drawerState.close() }
                                        println("Selected Option: $option")
                                    }
                                }
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = when (option) {
                                "Home" -> Icons.Default.Home
                                "Profile" -> Icons.Default.Person
                                "Gestion des Employers" -> Icons.Default.Settings
                                "Gestion des Clients" -> Icons.Default.Settings
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
        val userEmail by produceState(initialValue = "") {
            value = DataStoreManager.getEmail(context)
        }


        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Profile ") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                // Profile header section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFFEEF5FF))
                ) {

                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .align(Alignment.TopCenter)
                    ) {
                        drawPath(
                            path = Path().apply {
                                moveTo(0f, size.height * 0.7f)
                                cubicTo(
                                    size.width * 0.25f, size.height,
                                    size.width * 0.75f, size.height * 0.4f,
                                    size.width, size.height * 0.7f
                                )
                                lineTo(size.width, 0f)
                                lineTo(0f, 0f)
                                close()
                            },
                            color = primary
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 25.dp)
                    ) {
                        // Profile Picture
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                                .border(2.dp, Color.White, CircleShape)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "Medini Meriem", style = MaterialTheme.typography.titleLarge)

                    }
                }

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.End)
                ) {
                    // Button to open the dialog
                    TextButton(onClick = { showDialog = true }) {
                        Text(
                            text = "Edit Profile",
                            color = primary, // Change as per your theme
                            textAlign = TextAlign.Right,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Popup dialog for editing profile
                if (showDialog) {

                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = {
                            Text(text = "Edit Profile", fontWeight = FontWeight.Bold)
                        },
                        text = {
                            Column {
                                // Text fields to edit profile

                                val name = remember { mutableStateOf("") }
                                val lastname = remember { mutableStateOf("") }
                                val phoneNumber = remember { mutableStateOf("") }
                                val address = remember { mutableStateOf("") }
                                val password = remember { mutableStateOf("") }
                                val dateOfBirth = remember { mutableStateOf("") }
                                val errorMessage = remember { mutableStateOf("") }



                                TextField(
                                    value = name.value,
                                    onValueChange = { newValue ->
                                        if (newValue.length > 50) {
                                            errorMessage.value = "Name cannot exceed 50 characters"
                                        } else {
                                            errorMessage.value = ""
                                            name.value = newValue
                                        }
                                    },
                                    label = { Text("Name") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = errorMessage.value.isNotEmpty()
                                )
                                Text(
                                    text = errorMessage.value,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                TextField(
                                    value = lastname.value,
                                    onValueChange = { newValue ->
                                        if (newValue.length > 50) {
                                            errorMessage.value = "Name cannot exceed 50 characters"
                                        } else {
                                            errorMessage.value = ""
                                            lastname.value = newValue
                                        }
                                    },
                                    label = { Text("Last Name") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = errorMessage.value.isNotEmpty()
                                )
                                Text(
                                    text = errorMessage.value,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                TextField(
                                    value = phoneNumber.value,
                                    onValueChange = { newValue ->
                                        if (newValue.length > 50) {
                                            errorMessage.value = "Name cannot exceed 50 characters"
                                        } else {
                                            errorMessage.value = ""
                                            phoneNumber.value = newValue
                                        }
                                    },
                                    label = { Text("Address") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = errorMessage.value.isNotEmpty()
                                )
                                Text(
                                    text = errorMessage.value,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                TextField(
                                    value = address.value,
                                    onValueChange = { newValue ->
                                        if (newValue.length > 50) {
                                            errorMessage.value = "Name cannot exceed 50 characters"
                                        } else {
                                            errorMessage.value = ""
                                            address.value = newValue
                                        }
                                    },
                                    label = { Text("Phone Number") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = errorMessage.value.isNotEmpty()
                                )
                                Text(
                                    text = errorMessage.value,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                TextField(
                                    value = dateOfBirth.value,
                                    onValueChange = { newValue ->
                                        if (newValue.length > 50) {
                                            errorMessage.value = "Name cannot exceed 50 characters"
                                        } else {
                                            errorMessage.value = ""
                                            dateOfBirth.value = newValue
                                        }
                                    },
                                    label = { Text("Date of Birth") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = errorMessage.value.isNotEmpty()
                                )
                                Text(
                                    text = errorMessage.value,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                TextField(
                                    value = password.value,
                                    onValueChange = { newValue ->
                                        if (newValue.length > 50) {
                                            errorMessage.value = "Name cannot exceed 50 characters"
                                        } else {
                                            errorMessage.value = ""
                                            password.value = newValue
                                        }
                                    },
                                    label = { Text("Password") },
                                    modifier = Modifier.fillMaxWidth(),
                                    isError = errorMessage.value.isNotEmpty()
                                )
                                Text(
                                    text = errorMessage.value,
                                    color = Color.Red,
                                    style = MaterialTheme.typography.labelMedium
                                )

                            }
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                // Save changes
                                showDialog = false
                            }) {
                                Text("Save")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Cancel")
                            }
                        }
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), // Padding around the card
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Background color
                    shape = RoundedCornerShape(16.dp), // Rounded corners
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Shadow elevation
                ) {



                    Spacer(modifier = Modifier.width(16.dp)) // Space between elements

                    // Text content
                    Column {

                        Column {
                            val name = remember { mutableStateOf("") }
                            val lastname = remember { mutableStateOf("") }
                            val phoneNumber = remember { mutableStateOf("") }
                            val address = remember { mutableStateOf("") }
                            val password = remember { mutableStateOf("") }
                            val dateOfBirth = remember { mutableStateOf("") }
                            val errorMessage = remember { mutableStateOf("") }

                            profileViewModel.fetchProfile(userEmail, "")

                            ProfileOption(icon = Icons.Default.AccountBox, label = name.toString())
                            ProfileOption(icon = Icons.Default.AccountBox, label = lastname.toString())

                            ProfileOption(icon = Icons.Default.LocationOn, label = address.toString())

                            ProfileOption(icon = Icons.Default.Email, label = userEmail)
                        }
                    }


                }
            }
        }
    }
}

@Composable
fun ProfileOption(icon: ImageVector, label: String, isLogout: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle option click */ }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isLogout) Color.Red else Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = if (isLogout) Color.Red else Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    ProfileAdmin(navController = navController)
}