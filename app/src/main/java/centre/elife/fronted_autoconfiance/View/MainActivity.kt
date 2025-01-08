package centre.elife.fronted_autoconfiance.View

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.View.HomePage
import centre.elife.fronted_autoconfiance.View.Login
import centre.elife.fronted_autoconfiance.View.SignUp
import centre.elife.fronted_autoconfiance.ui.theme.Fronted_AutoConfianceTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Fronted_AutoConfianceTheme {
                val context = LocalContext.current
                val navController = rememberNavController()
                val context = LocalContext.current
                NavHost(
                    navController = navController,
                    startDestination = HomePageRoute
                ) {
                    composable<HomePageRoute> {
                        HomePage(navController)
                    }
                    composable<LoginRoute> {
                        Login(navController)
                    }

                    composable<SignupRoute> {
                        SignUp(navController)
                    }

                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    Fronted_AutoConfianceTheme {
        Login(NavHostController(LocalContext.current))

    }}



