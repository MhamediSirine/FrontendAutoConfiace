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
import centre.elife.fronted_autoconfiance.ClientProfileRoute
import centre.elife.fronted_autoconfiance.EmployeeProfileRoute
import centre.elife.fronted_autoconfiance.HomePageRoute
import centre.elife.fronted_autoconfiance.ProfileAdminRoute

import centre.elife.fronted_autoconfiance.ResetPasswordRoute
import centre.elife.fronted_autoconfiance.SendEmailRoute
import centre.elife.fronted_autoconfiance.SignupRoute
import centre.elife.fronted_autoconfiance.ViewModels.LoginViewModel
import centre.elife.fronted_autoconfiance.Views.EmployeeProfile.EmployeeProfile
import centre.elife.fronted_autoconfiance.ui.theme.secondary

@Composable
fun Login(navController: NavHostController, loginViewModel: LoginViewModel = LoginViewModel()) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val loading by loginViewModel.loading.observeAsState(false)

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
            Text("Welcome", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = secondary)
            Spacer(modifier = Modifier.height(20.dp))
            Text("AutoConfiance!", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = secondary)
            Spacer(modifier = Modifier.height(30.dp))

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                isError = errorMessage.isNotEmpty()
            )
            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                isError = errorMessage.isNotEmpty()
            )

            if (errorMessage.isNotEmpty()) {
                Text(text = errorMessage, color = Color.Red, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(10.dp))
            TextButton(onClick = { navController.navigate(SendEmailRoute) }) {
                Text("Forgot your password?", color = secondary, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        errorMessage = "Please fill in both email and password."
                    } else {
                        errorMessage = ""
                        loginViewModel.login(context, email, password)

                        loginViewModel.success.observeForever { isSuccess ->
                            if (isSuccess != null) {
                                if (isSuccess == true) {
                                    loginViewModel.role.observeForever { role ->
                                        if (role == "client") {
                                            navController.navigate(ClientProfileRoute)
                                        } else if (role == "employee") {
                                            navController.navigate(EmployeeProfileRoute)

                                        } else {
                                            navController.navigate(ProfileAdminRoute)
                                        }
                                    }
                                }
                                else if (isSuccess == false) {
                                    Toast.makeText(context, loginViewModel.errorMessage.value, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
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
                    Text("LOGIN", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text("Don't have an account?", color = Color.White, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = { navController.navigate(SignupRoute) }) {
                    Text("SIGN UP HERE", color = secondary)
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
