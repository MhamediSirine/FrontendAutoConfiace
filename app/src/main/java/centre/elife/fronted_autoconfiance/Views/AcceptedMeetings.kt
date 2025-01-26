package centre.elife.fronted_autoconfiance.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import centre.elife.fronted_autoconfiance.ViewModels.MeetingViewModel
import centre.elife.fronted_autoconfiance.data.models.Meeting
import centre.elife.fronted_autoconfiance.ui.theme.background
import centre.elife.fronted_autoconfiance.ui.theme.primary

@Composable
fun AcceptedMeetings(navController: NavHostController, meetingViewModel: MeetingViewModel = MeetingViewModel()) {

    var meetingsList by remember { mutableStateOf(emptyList<Meeting>()) }

    LaunchedEffect(Unit) {
        meetingViewModel.fetchAcceptedMeetings()

        meetingViewModel.acceptedMeetings.observeForever {
                meetings -> meetingsList = meetings
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        // Header Canvas
        CanvasHeaderCL("Accepted meetings")

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
                    MeetingItem(meeting)
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
fun MeetingItem(
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
    }
}