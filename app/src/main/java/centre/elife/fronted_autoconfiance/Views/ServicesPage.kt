package centre.elife.fronted_autoconfiance.Views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.AddEmployeeRoute
import centre.elife.fronted_autoconfiance.DetailsRoute
import centre.elife.fronted_autoconfiance.ListEmployeeRoute
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.ProfileAdminRoute
import centre.elife.fronted_autoconfiance.R
import centre.elife.fronted_autoconfiance.ServicesRoute
import centre.elife.fronted_autoconfiance.ui.theme.primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicePage(navController: androidx.navigation.NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
<<<<<<< Updated upstream
=======
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream
                val options = listOf("Services", "Profile", "Gestion des Employers", "Gestion des Clients", "Logout","About")
=======
                val options = listOf("Home", "Profile", "Gestion des Employers", "Gestion des Clients", "Logout", "About")
>>>>>>> Stashed changes
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
                                "Gestion des Employers" -> Icons.Default.Settings
                                "Add Employee" -> Icons.Default.Add
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Nos Services") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                item {
                    ServiceCard(R.drawable.oil_service, "Changement d'huile", "Gardez votre moteur en parfait état de marche avec notre service de changement d'huile rapide et efficace.")
                }
                item {
                    ServiceCard(R.drawable.checkup_service, "Contrôles routine de voiture", "Vérification des fluides, filtres et composants essentiels pour garantir la performance du véhicule.")
                }
                item {
                    ServiceCard(R.drawable.wash_service, "Lavage de voiture", "Lavage extérieur et intérieur pour redonner à votre voiture son éclat et la garder propre.")
                }
            }
        }
    }
}

@Composable
fun ServiceCard(
    imageId: Int,
    title: String,
    description: String
) {
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(bottom = 13.dp)
            )
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicesPreview() {
    ServicePage(navController =rememberNavController())
}