package com.example.siberianotes.presentation.screens.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.example.siberianotes.presentation.screens.base.UIEvent

@Immutable
sealed class AuthScreenEvent: UIEvent {

    object Default: AuthScreenEvent()

    object LoadingData: AuthScreenEvent()

    data class AuthorizationEvent(val login: String, val password: String): AuthScreenEvent()

    data class ShowError(var errorMessage: String?): AuthScreenEvent()
    }