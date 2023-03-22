package com.example.siberianotes.presentation.screens.auth.signup

import androidx.compose.runtime.Immutable
import com.example.siberianotes.domain.model.UserModel
import com.example.siberianotes.presentation.screens.base.UIEvent

@Immutable
sealed class SignUpScreenEvent: UIEvent {

    object Default: SignUpScreenEvent()

    object LoadingData: SignUpScreenEvent()

    data class RegistrationEvent(val user: UserModel): SignUpScreenEvent()

    data class ShowError(var errorMessage: String?): SignUpScreenEvent()
    }