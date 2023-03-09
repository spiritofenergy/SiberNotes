package com.example.siberianotes.presentation.screens.main

import com.example.siberianotes.domain.model.NoteModel
import com.example.siberianotes.presentation.screens.base.UIEvent
import javax.annotation.concurrent.Immutable

@Immutable
sealed class MainScreenEvent: UIEvent {
    object LoadingData: MainScreenEvent()
    data class ShowData(val data: List<NoteModel>): MainScreenEvent()
    data class ShowError(val errorMessage: String): MainScreenEvent()
}