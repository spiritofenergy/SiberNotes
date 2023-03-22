package com.example.siberianotes.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siberianotes.presentation.screens.auth.login.AuthScreen
import com.example.siberianotes.presentation.screens.auth.signup.SignUpScreen
import com.example.siberianotes.presentation.screens.main.MainScreen

sealed class Screens(val rout: String){
    object AuthScreenType: Screens(rout = "auth_screen")
    object RegistrationScreenType: Screens(rout = "registration_screen")
    object MainScreenType: Screens(rout = "main_screen")
}

@SuppressLint("ComposableDestinationInComposeScope")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.AuthScreenType.rout) {

        composable(route = Screens.AuthScreenType.rout) {
            AuthScreen(navController = navController)
        }
        composable(route = Screens.RegistrationScreenType.rout) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screens.MainScreenType.rout) {
            MainScreen(navController = navController)
        }
    }
}


