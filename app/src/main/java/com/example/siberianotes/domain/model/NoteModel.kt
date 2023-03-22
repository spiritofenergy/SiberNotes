package com.example.siberianotes.domain.model

import java.time.LocalDate

data class NoteModel(
    val id: String,
    val title: String,
    val subtitle: String,
    val date: String,
    val author: String
) {
}