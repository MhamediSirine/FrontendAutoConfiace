package centre.elife.fronted_autoconfiance.Views

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.HomePageRoute
import centre.elife.fronted_autoconfiance.SignupRoute
import centre.elife.fronted_autoconfiance.ViewModels.LoginViewModel
import centre.elife.fronted_autoconfiance.ui.theme.secondary

@Composable
fun Login(navController: NavHostController, loginViewModel: LoginViewModel = LoginViewModel()) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val loading by loginViewModel.loading.observeAsState(false)
    val loginResponse by loginViewModel.response.observeAsState(null)

    LaunchedEffect(loginResponse) {
        loginResponse?.let { response ->
            if (response.isSuccessful) {
                Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                navController.navigate(HomePageRoute)
            } else {
                errorMessage = response.errorBody()?.string() ?: "Invalid email or password."
            }
        }
    }

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
            // Welcome Text
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Welcome",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = secondary
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "AutoConfiance!",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = secondary
            )
            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = email,
                shape = RoundedCornerShape(16.dp),
                onValueChange = { email = it },
                label = { Text("Email", color = Color.Gray) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = password,
                shape = RoundedCornerShape(16.dp),
                onValueChange = { password = it },
                label = { Text("Password", color = Color.Gray) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { /* Navigate to Forgot Password */ }) {
                    Text(text = "Forgot your password?", color = secondary, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        errorMessage = "Please fill in both email and password."
                    } else {
                        errorMessage = ""
                        loginViewModel.login(email, password)
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF010035)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = !loading
            ) {
                if (loading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(text = "LOGIN", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Don't have an account?", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = { navController.navigate(SignupRoute) }) {
                    Text(text = "SIGN UP HERE", color = secondary)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    Login(navController = navController)
}
