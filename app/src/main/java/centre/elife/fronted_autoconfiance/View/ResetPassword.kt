package centre.elife.fronted_autoconfiance.View

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.ui.theme.primary
@Composable
fun ResetPasswordScreen(navController: NavHostController) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Design
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

        // Page Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Reset Password",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(200.dp))

            // Subtitle
            Text(
                text = "Your new password must be different from previously used passwords.",
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                color = primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password Input
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Confirm Password Input
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Confirm Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "Confirm Password Icon")
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Continue Button
            Button(
                onClick = { /* Handle reset password action */ },
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
@Preview(showBackground = true)
@Composable
fun ResetPasswordScreenPreview() {
    val navController = rememberNavController()
    ResetPasswordScreen(navController = navController)
}