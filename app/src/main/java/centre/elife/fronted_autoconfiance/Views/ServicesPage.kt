package centre.elife.fronted_autoconfiance.Views

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.R
import centre.elife.fronted_autoconfiance.ui.theme.primary
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicePage() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) } // State to control the popup dialog
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

                val options = listOf("Home", "Profile", "Gestion des Employers", "Gestion des Clients", "Logout","About")
                options.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
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
    imageId: Int, // Image URL
    title: String,
    description: String
) {
    // Card containing an image, title, and description
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
                .padding(16.dp) // Padding inside the card
        ) {
            // Image section (loads image from URL)

            Image(
                painter = painterResource(id = imageId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp) // Height for the image
                    .padding(bottom = 13.dp) // Space below the image
            )


            // Title
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = primary,
                modifier = Modifier.padding(bottom = 8.dp) // Space below the title
            )

            // Description
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2, // Limit description to two lines
                overflow = TextOverflow.Ellipsis // Ellipsis if text is too long
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ServicesPreview() {
    ServicePage()
}