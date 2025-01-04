package centre.elife.fronted_autoconfiance.View

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
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
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.R
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
        // Background shapes
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRect(color = Color(0xFF16213E)) // Dark Blue background
            drawRoundRect(
                color = Color(0xFF23395B), // Slightly lighter blue for the curved shape
                size = size.copy(width = size.width, height = size.height / 2),
                cornerRadius = CornerRadius(x = 50f, y = 50f),
                topLeft = Offset(x = 0f, y = 0f)
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

            // Input fields
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

            // Sign-Up Button
            Button(
                onClick = {
                    if (name.isNotBlank() && lastName.isNotBlank() && address.isNotBlank() &&
                        email.isNotBlank() && password.isNotBlank() && number.isNotBlank()
                    ) {
                        signupViewModel.signup(name, lastName, address, email, password)
                        navController.navigate(LoginRoute)
                        Toast.makeText(context, "Registered Successfully!", Toast.LENGTH_SHORT).show()
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