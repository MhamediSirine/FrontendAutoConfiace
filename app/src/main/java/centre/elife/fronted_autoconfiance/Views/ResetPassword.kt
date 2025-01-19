package centre.elife.fronted_autoconfiance.Views


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.MaterialTheme

import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavHostController
import centre.elife.fronted_autoconfiance.DataStoreManager.DataStoreManager
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.ViewModels.ResetPasswordViewModel
import centre.elife.fronted_autoconfiance.ui.theme.primary
@Composable
fun ResetPassword(navController: NavHostController,resetPasswordViewModel: ResetPasswordViewModel= ResetPasswordViewModel()) {
    val context = LocalContext.current
    var code by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    val userEmail by produceState(initialValue = "") {
        value = DataStoreManager.getEmail(context)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.TopCenter)
        ) {
            val path = Path().apply {
                moveTo(0f, size.height * 0.8f)
                cubicTo(
                    size.width * 0.25f, size.height,
                    size.width * 0.75f, size.height * 0.6f,
                    size.width, size.height * 0.8f
                )
                lineTo(size.width, 0f)
                lineTo(0f, 0f)
                close()
            }
            drawPath(
                path = path,
                color = primary
            )
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Reset Password",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = "Your new password must be different from previously used passwords.",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                color = primary
            )
            Text(text = "UserEmail: $userEmail")
            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = code,
                onValueChange = {code=it},
                label = { Text("Code") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = newPassword,
                onValueChange = {newPassword=it},
                label = { Text("New Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password Icon")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = {
                    if(code.isNotBlank() && newPassword.isNotBlank()){
                        resetPasswordViewModel.resetPassword(userEmail,code, newPassword)
                        resetPasswordViewModel.success.observeForever { response ->
                            if (response == true) {
                                navController.navigate(LoginRoute)
                            } else if (response == false) {
                                Toast.makeText(context, "Password reset code is not valid", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primary
                ),
            ) {
                Text("Continue")
            }
        }
    }
}

