package centre.elife.fronted_autoconfiance.Views


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.Models.Appointments
import centre.elife.fronted_autoconfiance.ui.theme.background
import centre.elife.fronted_autoconfiance.ui.theme.primary



@Composable
fun ClientListAppointment(navController: NavHostController) {
    val appointments = remember {
        mutableStateListOf(
            Appointments("John Doe", "10/01/2025", "10:30 AM", "Toyota", "ABC-1234", "Accepted"),
            Appointments("John Doe", "10/01/2025", "10:30 AM", "Toyota", "ABC-5897", "Accepted"),
          Appointments("Jane Smith", "11/01/2025", "2:00 PM", "Honda", "XYZ-5678", "Declined"),
            Appointments("Mike Johnson", "12/01/2025", "4:45 PM", "Ford", "DEF-8901", "Pending")
        )
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Header Canvas
        CanvasHeaderCL()

        // Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(appointments) { appointment ->
                    ClientAppointmentItem( appointment)
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }

        // Floating Action Button
        FloatingActionButton(
            onClick = { navController.navigate("formulaireRDV") },
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Appointment")
        }
    }
}



@Composable
fun CanvasHeaderCL() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
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
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Appointments",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ClientAppointmentItem(
    appointment: Appointments
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Name: ${appointment.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = background
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Date: ${appointment.date}", fontSize = 16.sp)
            Text(text = "Time: ${appointment.time}", fontSize = 16.sp)
            Text(text = "Car: ${appointment.carBrand}", fontSize = 16.sp)
            Text(text = "Register: ${appointment.carRegister}", fontSize = 16.sp)
            Text(text = "Status: ${appointment.status}", fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Preview
@Composable
fun ClientListAppointmentPreview() {
    ClientListAppointment(navController = rememberNavController())
}
