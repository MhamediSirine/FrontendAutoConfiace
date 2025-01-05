package centre.elife.fronted_autoconfiance.Views

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.R
import centre.elife.fronted_autoconfiance.SignupRoute
import centre.elife.fronted_autoconfiance.ViewModels.SignupViewModel
import centre.elife.fronted_autoconfiance.ui.theme.secondary


@Composable
fun SignUp(navController: NavHostController, signupViewModel: SignupViewModel = SignupViewModel()) {
    val context = LocalContext.current


    var name by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(color = Color(0xFF16213E))
            drawRoundRect(
                color = Color(0xFF23395B),
                size = size.copy(width = size.width, height = size.height / 2),
                cornerRadius = CornerRadius(x = 50f, y = 50f)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Sample Image",
                modifier = Modifier.size(150.dp),
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Register",
                style = MaterialTheme.typography.labelLarge,
                fontSize = 30.sp,
                color = secondary,
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Please Register To Login.",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = secondary,
            )
            Spacer(modifier = Modifier.height(20.dp))


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
                value = address,
                onValueChange = { address = it },
                label = { Text("Address", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = "Address Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password Icon") },
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = number,
                onValueChange = { number = it },
                label = { Text("Number", color = Color.Gray) },
                leadingIcon = { Icon(Icons.Default.Call, contentDescription = "Number Icon") },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))


            Button(
                onClick = {
                    if (name.isNotBlank() && lastName.isNotBlank() && address.isNotBlank() &&
                        email.isNotBlank() && password.isNotBlank() && number.isNotBlank()
                    ) {
                        signupViewModel.signup(name, lastName, address, email, password,number)
                        Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                        navController.navigate(SignupRoute)

                    } else {
                        Toast.makeText(context, "All fields are required.", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = secondary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
            ) {
                Text(text = "Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val navController = rememberNavController()
    SignUp(navController = navController)
}
