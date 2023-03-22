package com.example.siberianotes.presentation.screens.auth.signup

import androidx.compose.runtime.Immutable
import com.example.siberianotes.presentation.screens.base.UIState

@Immutable
data class SignUpScreenState(
    val isLoading: Boolean,
    val isSuccess: Boolean,
    val error: String? = null
) : UIState{

    companion object{
        fun initial() = SignUpScreenState(isLoading = false, isSuccess = false, error = null)
    }
}