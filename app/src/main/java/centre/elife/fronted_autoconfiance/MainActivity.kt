package centre.elife.fronted_autoconfiance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.Views.AdminProfile.AddEmployee
import centre.elife.fronted_autoconfiance.Views.AdminProfile.AdminProfile
import centre.elife.fronted_autoconfiance.Views.AdminProfile.GestionEmployers
import centre.elife.fronted_autoconfiance.Views.AdminProfile.UpdateEmployeePage
import centre.elife.fronted_autoconfiance.Views.ClientProfile.ClientProfile
import centre.elife.fronted_autoconfiance.Views.EmployeeProfile.EmployeeProfile
import centre.elife.fronted_autoconfiance.Views.HomePage
import centre.elife.fronted_autoconfiance.Views.Login
import centre.elife.fronted_autoconfiance.Views.ResetPassword
import centre.elife.fronted_autoconfiance.Views.SendEmail
import centre.elife.fronted_autoconfiance.Views.SignUp
import centre.elife.fronted_autoconfiance.ui.theme.Fronted_AutoConfianceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fronted_AutoConfianceTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ListEmployeeRoute,
                        modifier = Modifier.padding(innerPadding)    ) {

                        composable<HomePageRoute> {
                            HomePage(navController)
                        }
                        composable<LoginRoute> {
                            Login(navController)
                        }
                        composable<SignupRoute> {
                            SignUp(navController)
                        }
                        composable<SendEmailRoute> {
                            SendEmail(navController)
                        }
                        composable<ResetPasswordRoute> {
                            ResetPassword(navController)
                        }
                        composable<ProfileAdminRoute> {
                            AdminProfile(navController)
                        }
                        composable<ClientProfileRoute> {
                            ClientProfile(navController)
                        }
                        composable<EmployeeProfileRoute> {
                            EmployeeProfile(navController)
                        }
                        composable<AddEmployeeRoute> {
                            AddEmployee(navController)
                        }
                        composable<ModifyEmployeeRoute> {
                            UpdateEmployeePage(navController)
                        }

                        composable<ListEmployeeRoute> {
                            GestionEmployers(navController)

                    }}
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Fronted_AutoConfianceTheme {
        val navController = rememberNavController()
        SignUp(navController = navController)
    }
}
