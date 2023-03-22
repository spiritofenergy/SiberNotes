package com.example.siberianotes.presentation.screens.auth.signup

import android.util.Log
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
import com.example.siberianotes.domain.model.UserModel
import com.example.siberianotes.presentation.item.ErrorItem
import com.example.siberianotes.presentation.item.LoadingItem
import com.example.siberianotes.presentation.navigation.Screens

@Composable
fun SignUpScreen(navController: NavController) {
    var userId by remember { mutableStateOf(1)}
    var login by remember { mutableStateOf("")}
    var password by remember { mutableStateOf("")}
    var firstName by remember { mutableStateOf("")}
    var lastName by remember { mutableStateOf("")}
    var about by remember { mutableStateOf("")}

    val viewModel = hiltViewModel<SignUpScreenViewModel>()
    val state by viewModel.state.collectAsState()

    when{
            state.isLoading -> {
                LoadingItem()
            }
        state.isSuccess ->{
            viewModel.sendEvent(SignUpScreenEvent.Default)
            navController.navigate(Screens.AuthScreenType.rout)
        }
        state.error != null -> {
            ErrorItem(errorMessage = (state.error)) {
                viewModel.sendEvent(SignUpScreenEvent.Default)
            }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Registration",
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
                TextField(
                    value = firstName,
                    onValueChange = {firstName = it},
                    label = { Text(text = "First Name")},
                    modifier = Modifier
                        .padding(vertical = 8.dp))
                TextField(
                    value = lastName,
                    onValueChange = {lastName = it},
                    label = { Text(text = "Last Name")},
                    modifier = Modifier
                        .padding(vertical = 8.dp))
                TextField(
                    value = about,
                    onValueChange = {about = it},
                    label = { Text(text = "About me")},
                    modifier = Modifier
                        .padding(vertical = 8.dp))
                Button(
                    onClick = {
                        val userLogin = login.lowercase().trim()
                        val userPassword = password.lowercase().trim()
                        val userFirstName = firstName.lowercase().trim()
                        val userLastName = lastName.lowercase().trim()
                        val userAbout = about.lowercase().trim()
                        Log.d("check", "UserModel-1: $userId, $login, $password, $firstName, $lastName, $about")

                       // if(userLogin.isEmpty() && userPassword.isEmpty() && userFirstName.isEmpty() && userLastName.isEmpty()) {
                            val user = UserModel(
                              userId = null,
                              login = userLogin,
                              password = userPassword,
                              firstName = userFirstName,
                              lastName = userLastName,
                              about = userAbout
                            )
                            Log.d("check", "UserModel-2: $userId, $login, $password, $firstName, $lastName, $about")
                            viewModel.sendEvent(SignUpScreenEvent.RegistrationEvent(user))

                        },
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(text = "Sign Up")
                }
                Text(text = "Log In",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { navController.navigate(Screens.AuthScreenType.rout) })

            }
        }
    }
}