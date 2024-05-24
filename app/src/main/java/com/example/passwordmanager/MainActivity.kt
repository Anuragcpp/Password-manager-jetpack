package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.passwordmanager.feature_password.presentation.add_edit_password.AddEditPasswordScreen
import com.example.passwordmanager.feature_password.presentation.passwords.PasswordsScreen
import com.example.passwordmanager.feature_password.presentation.util.Screens
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordManagerTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.PasswordScreen.route ) {

                    composable(Screens.PasswordScreen.route){
                        PasswordsScreen(navController = navController)
                    }

                    composable(
                        Screens.AddEditPasswordScreen.route + "?passwordId={passwordId}",
                        arguments = listOf(
                            navArgument(name = "psswordId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditPasswordScreen()
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
    PasswordManagerTheme {
        Greeting("Android")
    }
}