package com.example.siberianotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.siberianotes.presentation.screens.main.MainScreen

sealed class Screens(val rout: String){
    object MainScreen: Screens(rout = "main_screen")
}

@Composable
fun SetupNavHost(navController: NavHostController) {
        NavHost(navController = navController, startDestination = Screens.MainScreen.rout){
            composable(route = Screens.MainScreen.rout){
                MainScreen(navController = navController)
            }
        }
}

