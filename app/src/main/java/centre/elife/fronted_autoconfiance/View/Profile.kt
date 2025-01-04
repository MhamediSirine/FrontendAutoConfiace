package centre.elife.fronted_autoconfiance.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Scaffold

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import centre.elife.fronted_autoconfiance.LoginRoute
import centre.elife.fronted_autoconfiance.R
import centre.elife.fronted_autoconfiance.ui.theme.Fronted_AutoConfianceTheme


@Composable
fun Profile(navController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize()
        ,
        containerColor = Color(0xFF010035)
    ) { padding ->
        val sergioFont = FontFamily(Font(R.font.sergio_trend))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Sample Image",
                modifier = Modifier.size(450.dp)
            )

            Text(
                text = "AUTO CONFIANCE",
                fontSize = 30.sp,
                fontFamily = sergioFont,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = "We make your car smarter",
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(70.dp))

            // Row with Buttons
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { navController.navigate(LoginRoute) },
                    modifier = Modifier.padding(end = 16.dp),
                    colors = ButtonDefaults.buttonColors( Color(0xFF4CAFAD))
                ) {
                    Text(text = "Login")
                }

                Button(
                    onClick = { navController.navigate(LoginRoute) },
                    modifier = Modifier.padding(start = 16.dp),
                    colors = ButtonDefaults.buttonColors( Color(0xFF4CAFAD))
                ) {
                    Text(text = "Sign Up")
                }
            }
        }
    }


    @Composable
    fun MyPagePreview() {
        Fronted_AutoConfianceTheme {
            Profile(navController)
        }
    }



}

