package com.example.siberianotes.presentation.screens.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.example.siberianotes.presentation.screens.base.UIState

@Immutable
data class AuthScreenState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null
) : UIState{

    companion object{
        fun initial() = AuthScreenState(isLoading = false, isSuccess = false, error = null)
    }
}