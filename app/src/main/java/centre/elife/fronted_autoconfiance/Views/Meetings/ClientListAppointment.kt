package centre.elife.fronted_autoconfiance.Views

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import centre.elife.fronted_autoconfiance.ViewModels.MeetingViewModel
import centre.elife.fronted_autoconfiance.data.models.Meeting
import centre.elife.fronted_autoconfiance.ui.theme.background
import centre.elife.fronted_autoconfiance.ui.theme.primary

data class Appointments(
    val name: String,
    val date: String,
    val time: String,
    val carBrand: String,
    val carRegister: String,
    val status: String
)

@Composable
fun ClientListAppointment(navController: NavHostController, meetingViewModel: MeetingViewModel = MeetingViewModel()) {

    var meetingsList by remember { mutableStateOf(emptyList<Meeting>()) }

    LaunchedEffect(Unit) {
        meetingViewModel.fetchPendingMeetings()

        meetingViewModel.pendingMeetings.observeForever {
            meetings -> meetingsList = meetings
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Header Canvas
        CanvasHeaderCL("Pending meetings")

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
                items(meetingsList) { meeting ->
                    ClientAppointmentItem(meeting)
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }

    }
}

@Composable
fun CanvasHeaderCL(title: String) {
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
                text = title,
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
    meeting: Meeting,
    meetingViewModel: MeetingViewModel = MeetingViewModel()
) {
    val context = LocalContext.current
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
                text = "Name: ${meeting.name} ${meeting.lastName}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = background
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Date: ${meeting.day} / ${meeting.month} / ${meeting.year}", fontSize = 16.sp)
            Text(text = "Time: ${meeting.hour} : ${meeting.minute}", fontSize = 16.sp)
            Text(text = "Car: ${meeting.carType}", fontSize = 16.sp)
            Text(text = "Register: ${meeting.carLicence}", fontSize = 16.sp)
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                meetingViewModel.handleMeeting(meeting.id, true)

                meetingViewModel.success.observeForever { success ->
                    if (success) Toast.makeText(context, "Meeting has been accepted", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(context, "Error accepting meeting", Toast.LENGTH_SHORT).show()
                }

            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Accept",
                    tint = Color.Green
                )
            }

            IconButton(onClick = {
                meetingViewModel.handleMeeting(meeting.id, false)

                meetingViewModel.success.observeForever { success ->
                    if (success) Toast.makeText(context, "Meeting has been rejected", Toast.LENGTH_SHORT).show()
                    else Toast.makeText(context, "Error rejecting meeting", Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Refuse",
                    tint = Color.Red
                )
            }
        }
    }
}