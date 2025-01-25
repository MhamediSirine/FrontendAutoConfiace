package centre.elife.fronted_autoconfiance.Views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.*
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.ui.theme.primary
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPage(navController: NavHostController) {
    // State for the sidebar (drawer)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false)} // State to control the popup dialog
    val context = LocalContext.current

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
                val options = listOf("Home", "Profile", "Gestion des Employers", "Gestion des Clients", "Logout","About")
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
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("A propos") },
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


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), // Outer padding around the card
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Light gray background for the card
                    shape = RoundedCornerShape(16.dp), // Rounded corners for a modern look
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Shadow for a subtle 3D effect
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp) // Inner padding for the card content
                    ) {
                        // Title
                        Text(
                            text = "À Propos de Auto Confiance",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = primary,
                            modifier = Modifier.padding(bottom = 8.dp) // Space below the title
                        )

                        // Description
                        Text(
                            text = "\"Auto Confiance\" est votre partenaire de confiance pour l’entretien et la vidange de votre véhicule. Avec une équipe expérimentée et des services rapides, fiables et abordables, nous veillons à ce que votre moteur reste en parfait état.",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                lineHeight = 22.sp
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Justify, // Aligns the text for a clean look
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp), // Padding around the card
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Background color
                    shape = RoundedCornerShape(16.dp), // Rounded corners
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Shadow elevation
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp) // Inner padding for the card content
                    ) {
                        // Title
                        Text(
                            text = "Contactez-nous",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = primary,
                            modifier = Modifier.padding(bottom = 8.dp) // Space below the title
                        )

                        // Contact options
                        ContactOption(icon = Icons.Default.Email, label = "autoconfience@gmail.com")
                        ContactOption(icon = Icons.Default.Phone, label = "+216 71 180 196")
                        ContactOption(icon = Icons.Default.LocationOn, label = "Zone Industrielle Kheireddine, Lac 3, Tunis")
                        ContactOption(icon = Icons.Default.Search, label = "https://vidange.tn/services/agence/20000033")

                        Button(
                            onClick = {
                                val latitude = 36.8065
                                val longitude = 10.1815
                                val label = "Auto Confiance, Zone Industrielle Kheireddine, Lac 3, Tunis"

                                val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude($label)")
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                                    setPackage("com.google.android.apps.maps") // Opens specifically in Google Maps app
                                }

                                context.startActivity(mapIntent)

                            },

                            colors = ButtonDefaults.buttonColors(
                                containerColor = primary
                            ),

                            modifier = Modifier
                                .fillMaxWidth() // Make the button take full width
                                .padding(top = 16.dp) // Padding above the button
                        ) {
                            Text(
                                text = "Localisation sur map",
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp)
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun ContactOption(icon: ImageVector, label: String) {
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
            tint = primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    val navController = rememberNavController()
    DetailsPage(navController = navController)
}