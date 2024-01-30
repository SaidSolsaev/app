package no.uio.ifi.in2000.smsolsae.oblig1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.uio.ifi.in2000.smsolsae.oblig1.ui.converter.UnitConverter
import no.uio.ifi.in2000.smsolsae.oblig1.ui.palindrome.PalindromeChecker
import no.uio.ifi.in2000.smsolsae.oblig1.ui.theme.Smsolsae_oblig1Theme

sealed class Screens(val route: String){
    data object PalindromeScreen: Screens("ui.palindrome.PalindromeScreen.kt")
    data object UnitConverter: Screens("ui.converter.UnitConverter.kt")
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Smsolsae_oblig1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.PalindromeScreen.route,
                    ){
                        composable(Screens.PalindromeScreen.route){ PalindromeChecker(navController) }
                        composable(Screens.UnitConverter.route){ UnitConverter(navController)}
                    }
                }
            }
        }
    }
}