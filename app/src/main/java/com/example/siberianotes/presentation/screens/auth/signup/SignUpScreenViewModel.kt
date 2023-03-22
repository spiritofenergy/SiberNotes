package com.example.siberianotes.presentation.screens.auth.signup

import androidx.lifecycle.viewModelScope
import com.example.siberianotes.domain.usecase.AuthUseCase
import com.example.siberianotes.presentation.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseViewModel<SignUpScreenState, SignUpScreenEvent>(){

    private val reducer = SignUpScreenReducer(
        initial = SignUpScreenState.initial(),
        authUseCase = authUseCase,
        viewModelScope = viewModelScope
            )
     override val state: StateFlow<SignUpScreenState>
     get() = reducer.state

    fun sendEvent(event: SignUpScreenEvent){
        reducer.sendEven(event)
    }



}