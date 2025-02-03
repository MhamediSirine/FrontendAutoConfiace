package centre.elife.fronted_autoconfiance.Views.AdminProfile
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import centre.elife.fronted_autoconfiance.Views.DetailsPage
import kotlinx.coroutines.launch
import centre.elife.fronted_autoconfiance.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminProfile(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }

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
                val options = listOf("Services", "Profile","Employee Management", "Add Employee", "Logout", "About")
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
                    title = { Text("Admin Profile") },
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
                Spacer(modifier = Modifier.height(8.dp))
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {


                    AdminProfileCard()
                    Spacer(modifier = Modifier.height(15.dp))
                    TextButton(onClick = { showDialog = true }) {
                        Text(
                            text = "Edit Profile",
                            color = primary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                if (showDialog) {
                    UpdateAdminProfileDialog(onDismiss = { showDialog = false })
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfilePreview() {
    AdminProfile(navController = rememberNavController())

}
