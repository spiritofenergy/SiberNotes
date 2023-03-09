package com.example.siberianotes.presentation.screens.main

import com.example.siberianotes.domain.model.NoteModel
import com.example.siberianotes.presentation.screens.base.UIState
import javax.annotation.concurrent.Immutable

@Immutable
data class MainScreenState(
    val isLoading: Boolean,
    val data: List<NoteModel>,
    val error: String? = null
): UIState {
    companion object{
        fun initial() = MainScreenState(
            isLoading = true,
            data = emptyList(),
            error = null)
    }
}