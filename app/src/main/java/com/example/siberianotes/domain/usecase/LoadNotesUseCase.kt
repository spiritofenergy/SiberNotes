package com.example.siberianotes.domain.usecase

import com.example.siberianotes.data.DomainRepositoryImpl
import com.example.siberianotes.domain.model.NoteModel
import com.example.siberianotes.domain.repository.DomainRepository
import javax.inject.Inject

class LoadNotesUseCase @Inject constructor(
    private val domainRepository: DomainRepositoryImpl
) : BaseUseCase<List<NoteModel>>() {
    override suspend fun invoke(): List<NoteModel> = domainRepository.getAllNotes()
}