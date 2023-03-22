package com.example.siberianotes.presentation.screens.auth.signup

import com.example.siberianotes.domain.usecase.AuthUseCase
import com.example.siberianotes.presentation.screens.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class SignUpScreenReducer(
    initial:SignUpScreenState,
    private val authUseCase: AuthUseCase,
    private val viewModelScope: CoroutineScope
    ): Reducer<SignUpScreenState, SignUpScreenEvent>(initialVal = initial) {

    override fun reduce(oldState: SignUpScreenState, event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.Default -> setState(
                oldState.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = null
                )
            )
            is SignUpScreenEvent.RegistrationEvent -> {
                sendEven(SignUpScreenEvent.LoadingData)
                viewModelScope.launch {
                    try {
                        authUseCase.registerNewUser(event.user).collect {
                                if (it.data != null) {
                                    setState(oldState.copy(isLoading = false, isSuccess = true))
                                } else if (it.message != null) {
                                    sendEven(SignUpScreenEvent.ShowError(errorMessage = "Data is Empty"))
                                } else {
                                    sendEven(SignUpScreenEvent.LoadingData)
                                }
                            }
                    } catch (e: Exception) {
                        sendEven(SignUpScreenEvent.ShowError(errorMessage = "Что-то пошло не так"))
                    }
                }
            }
            is SignUpScreenEvent.LoadingData -> {
                setState(oldState.copy(isLoading = false, isSuccess = false))
            }

            is SignUpScreenEvent.ShowError -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        isSuccess = false,
                        error = event.errorMessage))
            }
        }
    }
}
