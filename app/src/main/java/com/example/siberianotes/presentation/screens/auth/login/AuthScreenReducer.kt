package com.example.siberianotes.presentation.screens.auth.login

import com.example.siberianotes.domain.usecase.AuthUseCase
import com.example.siberianotes.presentation.screens.base.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class AuthScreenReducer(
    initial:AuthScreenState,
    private val authUseCase: AuthUseCase,
    private val viewModelScope: CoroutineScope
    ): Reducer<AuthScreenState, AuthScreenEvent>(initialVal = initial) {

    override fun reduce(oldState: AuthScreenState, event: AuthScreenEvent) {
        when (event) {
            is AuthScreenEvent.Default -> setState(
                oldState.copy(
                    isLoading = false,
                    isSuccess = false,
                    error = null
                )
            )
            is AuthScreenEvent.AuthorizationEvent -> {
                sendEven(AuthScreenEvent.LoadingData)
                viewModelScope.launch {
                    try {
                        authUseCase.loginUser(email = event.login, password = event.password)
                            .collect {
                                if (it.data != null) {
                                    setState(oldState.copy(isLoading = false, isSuccess = true))
                                } else if (it.message != null) {
                                    sendEven(AuthScreenEvent.ShowError(errorMessage = "Data is Empty"))
                                } else {
                                    sendEven(AuthScreenEvent.LoadingData)
                                }
                            }
                    } catch (e: Exception) {
                        sendEven(AuthScreenEvent.ShowError(errorMessage = "Что-то пошло не так"))
                    }
                }
            }
            is AuthScreenEvent.LoadingData -> {
                setState(oldState.copy(isLoading = false, isSuccess = false))
            }

            is AuthScreenEvent.ShowError -> {
                setState(
                    oldState.copy(
                        isLoading = false,
                        isSuccess = false,
                        error = event.errorMessage))
            }
        }
    }
}
