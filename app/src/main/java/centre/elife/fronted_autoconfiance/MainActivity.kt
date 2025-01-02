package centre.elife.fronted_autoconfiance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import centre.elife.fronted_autoconfiance.View.HomePage
import centre.elife.fronted_autoconfiance.ui.theme.Fronted_AutoConfianceTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fronted_AutoConfianceTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = HomePageRoute
                ) {
                    composable<HomePageRoute> {
                        HomePage(navController)
                    }
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
        Greeting("sirine")
    }
}
