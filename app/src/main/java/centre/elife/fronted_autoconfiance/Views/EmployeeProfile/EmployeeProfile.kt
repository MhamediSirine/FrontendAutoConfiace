package centre.elife.fronted_autoconfiance.Views.AdminProfile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.Models.Employee
import centre.elife.fronted_autoconfiance.Views.EmployeeProfile.EmployeeProfileCard
import centre.elife.fronted_autoconfiance.Views.EmployeeProfile.UpdateEmployeeProfileDialog
import kotlinx.coroutines.launch
import centre.elife.fronted_autoconfiance.ui.theme.primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployeeProfile(navController: NavHostController) {
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

                // Sidebar options
                val options = listOf("Home", "Profile", "Logout","Gestion Client", "About")
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Handle sidebar option click
                                scope.launch { drawerState.close() }
                                println("Selected Option: $option")
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = when (option) {
                                "Home" -> Icons.Default.Home
                                "Profile" -> Icons.Default.Person
                                "Gestion Client" -> Icons.Default.Settings
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
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                // Embedded Header Code
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color(0xFFEEF5FF))
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
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
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = "Medini Meriem",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    TextButton(onClick = { showDialog = true }) {
                        Text(
                            text = "Edit Profile",
                            color = primary,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    EmployeeProfileCard()
                }

                if (showDialog) {
                    UpdateEmployeeProfileDialog(onDismiss = { showDialog = false })
                }
            }
        }
    }
}

@Preview
@Composable
fun EmployeeProfilePreview() {
    EmployeeProfile(navController = rememberNavController())
}