package centre.elife.fronted_autoconfiance.Views.Meetings


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.ViewModels.requestMeetingViewModel
import centre.elife.fronted_autoconfiance.ui.theme.primary
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulaireRDV(navController: NavHostController,requestMeetingViewModel: requestMeetingViewModel= requestMeetingViewModel()) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var carType by remember { mutableStateOf("") }
    var carLicence by remember { mutableStateOf("") }


    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    val context = LocalContext.current

    val userEmail by produceState(initialValue = "") {
        value = DataStoreManager.getEmail(context)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Appointment") })
            CanvasHeaderF()
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp)
        ) {


            Column {
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
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    label = { Text("Phone Number", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Phone Icon") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))


                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                        date = "$selectedDay/${selectedMonth}/$selectedYear"
                    },
                    year, month, day
                )


                val timePickerDialog = TimePickerDialog(
                    context,
                    { _, selectedHour, selectedMinute ->
                        time = String.format("%02d:%02d", selectedHour, selectedMinute)
                    },
                    hour, minute, true
                )

                OutlinedButton(
                    onClick = { datePickerDialog.show() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (date.isEmpty()) "Pick Date" else "Date: $date")
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedButton(
                    onClick = { timePickerDialog.show() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = if (time.isEmpty()) "Pick Time" else "Time: $time")
                }
                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = carType,
                    onValueChange = { carType = it },
                    label = { Text("Car Brand", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Build, contentDescription = "Brand Icon") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = carLicence,
                    onValueChange = { carLicence = it },
                    label = { Text("Car Register", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Build, contentDescription = "Register Icon") },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            Log.e("hour", hour.toString())
                            Log.e("minute", minute.toString())
                          Log.e("day", day.toString())
                            Log.e("month", month.toString())
                            Log.e("year", year.toString())
                            requestMeetingViewModel.requestMeeting(
                                userEmail,
                                carType,
                                carLicence,
                                name,
                                lastName,
                                phoneNumber,
                                hour,
                                minute,
                                day,
                                month,
                                year,
                                "")
                            requestMeetingViewModel.success.observeForever {
                                if (it) {
                                    Toast.makeText(context, "Meeting Requested", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()

                                }
                                else{
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()

                                }

                            }

                        },
                        modifier = Modifier.weight(1f).padding(end = 8.dp)
                    ) {
                        Text("Confirm")
                    }
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

@Composable
fun CanvasHeaderF() {
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
                text = "Add Appointments",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun FormulaireRDVPreview() {
    FormulaireRDV(navController = rememberNavController())
}
