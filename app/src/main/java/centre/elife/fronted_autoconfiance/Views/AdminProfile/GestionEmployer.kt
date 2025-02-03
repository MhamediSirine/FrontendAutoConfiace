
package centre.elife.fronted_autoconfiance.Views.AdminProfile

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import centre.elife.fronted_autoconfiance.AddEmployeeRoute
import centre.elife.fronted_autoconfiance.AdminRoute
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.DetailsRoute
import centre.elife.fronted_autoconfiance.ListEmployeeRoute
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.Models.Employee
import centre.elife.fronted_autoconfiance.ModifyEmployeeRoute
import centre.elife.fronted_autoconfiance.ServicesRoute
import centre.elife.fronted_autoconfiance.ViewModels.ListEmployeeViewModel
import centre.elife.fronted_autoconfiance.data.models.ProfileDetails

import centre.elife.fronted_autoconfiance.ui.theme.primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestionEmployers(navController: NavHostController,ListEmployeeViewModel: ListEmployeeViewModel = ListEmployeeViewModel()) {
    var employees by remember { mutableStateOf(listOf<ProfileDetails>()) }
val context = LocalContext.current
    val employeeToDelete = remember { mutableStateOf<Employee?>(null) }
    val showDeleteConfirmation = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = androidx.compose.material3.DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        val token = DataStoreManager.getToken(context);
        ListEmployeeViewModel.getEmployees(token)
        ListEmployeeViewModel.success.observeForever { success ->
            if (!success) {

            } else {
                ListEmployeeViewModel.employees.observeForever { newEmployees ->
                    employees = newEmployees
                }


            }

    }}



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
                                        "Profile" -> navController.navigate(AdminRoute)
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
                    title = { Text("Profile") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open Menu")
                        }
                    }
                )
            }
        )  { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(employees) { employee ->
                EmployeeItem(
                    employee = employee,
                    onDeleteClick = {


                    },
                    onModifyClick = {
                        coroutineScope.launch {
                            DataStoreManager.setEmailToUpdate(context, employee.email);
                            navController.navigate(ModifyEmployeeRoute)
                        }

                    }
                )
            }
        }

        if (showDeleteConfirmation.value) {
            ConfirmDeleteDialog(
                onDismiss = { showDeleteConfirmation.value = false },
                onConfirm = {

                   // employees.remove(employeeToDelete.value)

                    employeeToDelete.value = null
                    showDeleteConfirmation.value = false
                }
            )
        }
    }
}}

@Composable
fun TopSectionBox(scope: CoroutineScope, drawerState: DrawerState) {
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
        IconButton(
            onClick = { scope.launch { drawerState.open() } },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Menu, contentDescription = "Open Menu", tint = Color.White)
        }
        Text(
            text = "Employee Management",
            style = MaterialTheme.typography.headlineSmall.copy(color = Color.White),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun EmployeeItem(

    employee: ProfileDetails,

    onDeleteClick: () -> Unit,
    onModifyClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Column containing the employee's name and post
        Column(
            modifier = Modifier.weight(1f) // Ensure this takes up available space
        ) {
            Text(employee.name, fontWeight = FontWeight.Bold)
            Text(employee.lastName, style = MaterialTheme.typography.labelMedium)
            Text(employee.email, style = MaterialTheme.typography.labelMedium)

        }

        Spacer(modifier = Modifier.width(8.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
            IconButton(onClick = onModifyClick) {
                Icon(Icons.Default.Edit, contentDescription = "Modify")
            }
        }
    }
}









@Composable
fun ConfirmDeleteDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Confirm Deletion") },
        text = { Text("Are you sure you want to delete this employee?") },
        confirmButton = {
            Button(onClick = onConfirm) { Text("Delete") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        }
    )
}





@Preview
@Composable
fun GestionEmployersPreview() {
    GestionEmployers(navController = NavHostController(LocalContext.current))
}
