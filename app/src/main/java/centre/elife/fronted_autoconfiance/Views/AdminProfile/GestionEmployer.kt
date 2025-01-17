import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import centre.elife.fronted_autoconfiance.Models.Employee
import centre.elife.fronted_autoconfiance.ui.theme.primary
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GestionEmployers(navController: NavHostController) {
    val employees = remember {
        mutableStateListOf(
            Employee( id="1", "John", "Doe", "Tunis", "john.doe@gmail.com", "1234", "25", "rh",  null),
            Employee( id="2", "Jane", "Smith", "Paris", "jane.smith@gmail.com", "5678", "30", "manager", null),
            Employee( id="3", "Bob", "Johnson", "London", "bob.johnson@gmail.com", "9012", "28", "dev", null)
        )
    }
    val employeeToDelete = remember { mutableStateOf<Employee?>(null) }
    val showDeleteConfirmation = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopSectionBox (scope = rememberCoroutineScope(), drawerState = rememberDrawerState(
            DrawerValue.Closed))
                 },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("AddEmployer") }) {
                Icon(Icons.Default.Add, contentDescription = "Add Employer")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(employees) { employee ->
                EmployeeItem(
                    employee = employee,
                    onDeleteClick = {
                        employeeToDelete.value = employee
                        showDeleteConfirmation.value = true
                    },
                    onModifyClick = {
                        navController.navigate("ModifyEmployerPage/${employee.id}")
                    }
                )
            }
        }

        if (showDeleteConfirmation.value) {
            ConfirmDeleteDialog(
                onDismiss = { showDeleteConfirmation.value = false },
                onConfirm = {
                    employees.remove(employeeToDelete.value)
                    employeeToDelete.value = null
                    showDeleteConfirmation.value = false
                }
            )
        }
    }
}

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
                color = primary// Replace with your theme's primary color
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
            text = "Gestion des Employers",
            style = MaterialTheme.typography.headlineSmall.copy(color = Color.White),
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun EmployeeItem(
    employee: Employee, // Accept an Employee object
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
            Text(employee.poste, style = MaterialTheme.typography.labelMedium)
        }

        Spacer(modifier = Modifier.width(8.dp)) // Add some space between the text and buttons

        // Row containing the buttons for delete and modify
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
