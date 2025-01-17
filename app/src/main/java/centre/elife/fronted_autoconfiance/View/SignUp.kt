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
import androidx.compose.ui.graphics.Path
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
import centre.elife.fronted_autoconfiance.ui.theme.primary
import centre.elife.fronted_autoconfiance.ui.theme.secondary


@Composable
fun SignUp(navController: NavHostController) {

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
                color = primary // Purple Color
            )
        }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {

            Image (
                painter = painterResource(id = R.drawable.car),
                contentDescription = "Sample Image",
                modifier = Modifier.size(150.dp),alignment = Alignment.Center,

                )

            // Logo
Column {



    Spacer(modifier = Modifier.height(20.dp))


    Text(
        text = "Register",
       style = MaterialTheme.typography.labelLarge,
        fontSize = 30.sp,
        color = secondary,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = "Please Register To Login. ",
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = secondary,
    )
    Spacer(modifier = Modifier.height(20.dp))

    TextField( value = name,
        shape =  RoundedCornerShape(16.dp),
        onValueChange = { name = it },
        label = { Text("Name", color = Color.Gray,) },
        leadingIcon = {
            Icon(Icons.Default.Person, contentDescription = "Username Icon")},
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            )

    Spacer(modifier = Modifier.height(20.dp))

    TextField( value = lastName,
        shape =  RoundedCornerShape(16.dp),
        onValueChange = { lastName = it },
        label = { Text("LastName", color = Color.Gray,) },
        leadingIcon = {
            Icon(Icons.Default.Person, contentDescription = "Username Icon")},
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            ,)
    Spacer(modifier = Modifier.height(20.dp))

    TextField( value = address,
        shape =  RoundedCornerShape(16.dp),
        onValueChange = { address = it },
        label = { Text("Address", color = Color.Gray,) },
        leadingIcon = {
            Icon(Icons.Default.LocationOn, contentDescription = "Username Icon")},
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            ,)
    Spacer(modifier = Modifier.height(20.dp))

    TextField( value = email,
        shape =  RoundedCornerShape(16.dp),
        onValueChange = { email = it },
        label = { Text("Email", color = Color.Gray,) },
        leadingIcon = {
            Icon(Icons.Default.Email, contentDescription = "Username Icon")},
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            ,)
    Spacer(modifier = Modifier.height(20.dp))

    TextField( value = password,
        shape =  RoundedCornerShape(16.dp),
        onValueChange = { password = it },
        label = { Text("Password", color = Color.Gray,) },
        leadingIcon = {
            Icon(Icons.Default.Lock, contentDescription = "Username Icon")},
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            ,)
    Spacer(modifier = Modifier.height(20.dp))

    TextField( value = number,
        shape =  RoundedCornerShape(16.dp),
        onValueChange = { number = it },
        label = { Text("Number", color = Color.Gray,) },
        leadingIcon = {
            Icon(Icons.Default.Call, contentDescription = "Username Icon")},
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            ,)

    Spacer(modifier = Modifier.height(20.dp))

    Button(
        onClick = {navController.navigate(LoginRoute)
            Toast.makeText(context, "Register Successfully!", Toast.LENGTH_SHORT).show()
                  },
        shape =  RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = primary
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),


    ) {
        Text(text = "Sign Up", color = Color.White, fontWeight = FontWeight.Bold)
    }



}



        }
    }

    @Preview (showBackground = true)
    @Composable
    fun SignUpPreview() {
        val navController = rememberNavController()
        SignUp(navController = navController)
    }
