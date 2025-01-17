import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.Models.Employee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyEmployerPage(
    navController: NavHostController,
    employee: Employee,
    onSave: (Employee) -> Unit,
    employeeId: String?
) {
    var name by remember { mutableStateOf(employee.name) }
    var lastName by remember { mutableStateOf(employee.lastName) }
    var post by remember { mutableStateOf(employee.poste) }
    var email by remember { mutableStateOf(employee.email) }
    var address by remember { mutableStateOf(employee.address) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Modify Employer") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
            TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") })
            TextField(value = post, onValueChange = { post = it }, label = { Text("Post") })
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            TextField(value = address, onValueChange = { address = it }, label = { Text("Address") })


            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val updatedEmployer = employee.copy(
                        name = name,
                        lastName = lastName,
                        poste = post,
                        email = email,
                        address = address,
                    )
                    onSave(updatedEmployer)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Changes")
            }
        }
    }
}

@Preview
@Composable
fun ModifyEmployerPagePreview() {}

