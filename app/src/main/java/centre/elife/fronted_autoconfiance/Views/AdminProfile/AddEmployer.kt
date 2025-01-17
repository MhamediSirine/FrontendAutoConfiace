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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.Models.Employee

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEmployer(navController: NavHostController, onAdd: (Employee) -> Unit) {
    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var post by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }


    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Employer") }) }
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
            TextField(value = birthDate, onValueChange = { birthDate = it }, label = { Text("Birth Date") })
            TextField(value = password, onValueChange = { password = it }, label = { Text("Password") })


            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val newEmployer = Employee(
                        name = name,
                        lastName = lastName,
                        address = address,
                        email = email,
                        poste = post,
                        birthDate=birthDate,
                        password="123456",
                        photo=null

                    )
                    onAdd(newEmployer)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Employer")
            }
        }
    }
}

@Preview
@Composable
fun AddEmployerPagePreview() {
    AddEmployer(navController = rememberNavController(), onAdd = {})
}