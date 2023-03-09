package com.example.siberianotes.domain.repository

import com.example.siberianotes.domain.model.NoteModel

interface DomainRepository {
    suspend fun getAllNotes(): List<NoteModel>
}