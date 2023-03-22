package com.example.siberianotes.presentation.screens.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.siberianotes.presentation.item.ErrorItem
import com.example.siberianotes.presentation.item.LoadingItem
import com.example.siberianotes.presentation.navigation.Screens
import kotlin.math.log

@Composable
fun AuthScreen(navController: NavController) {
    var login by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}

    val viewModel = hiltViewModel<AuthScreenViewModel>()
    val state by viewModel.state.collectAsState()

    when{
            state.isLoading -> {
                LoadingItem()
            }
        state.isSuccess ->{
            viewModel.sendEvent(AuthScreenEvent.Default)
            navController.navigate(Screens.MainScreenType.rout)
        }
        state.error != null -> {
            ErrorItem(errorMessage = (state.error)) {
                viewModel.sendEvent(AuthScreenEvent.Default)
            }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Authorisation",
                modifier = Modifier
                    .padding(vertical = 8.dp)
                )
                TextField(
                    value = login,
                    onValueChange = {login = it},
                    label = { Text(text = "Login")},
                    modifier = Modifier
                        .padding(vertical = 8.dp))
                TextField(
                    value = password,
                    onValueChange = {password = it},
                    label = { Text(text = "password")},
                    modifier = Modifier
                        .padding(vertical = 8.dp))
                Button(
                    onClick = {
                    viewModel.sendEvent(AuthScreenEvent.AuthorizationEvent(
                        login = login.lowercase().trim(),
                        password = password.lowercase().trim())) },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = "Lod In")
                }
                Text(text = "Registration",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { navController.navigate(Screens.RegistrationScreenType.rout) })

            }
        }
    }
}