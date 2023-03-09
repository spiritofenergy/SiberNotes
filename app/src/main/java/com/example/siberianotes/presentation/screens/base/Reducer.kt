package com.example.siberianotes.presentation.screens.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Reducer <S: UIState, E:UIEvent>(initialVal: S) {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialVal)
    val state: StateFlow<S>
    get() = _state

    fun sendEven(event:E){
       reduce(_state.value, event)
    }
    fun setState(newsState: S){
        _state.tryEmit(newsState)
    }
    abstract fun reduce(oldState:S, event:E)
}
interface UIState
interface UIEvent