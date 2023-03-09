package com.example.siberianotes.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.siberianotes.domain.usecase.LoadNotesUseCase
import com.example.siberianotes.presentation.screens.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val loadNotesUseCase: LoadNotesUseCase
): BaseViewModel<MainScreenState, MainScreenEvent>() {

    private val reducer = MainScreenReducer(
        initial = MainScreenState.initial(),
        useCase = loadNotesUseCase,
        viewModelScope = viewModelScope
    )
    override val state: StateFlow<MainScreenState>
        get() = reducer.state

    init {
        sendEvent(MainScreenEvent.LoadingData)
    }

    fun sendEvent(event: MainScreenEvent){
        reducer.sendEven(event)
    }
}