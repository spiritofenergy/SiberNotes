package com.example.siberianotes.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.siberianotes.domain.model.NoteModel
import com.example.siberianotes.domain.repository.DomainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.time.LocalDate
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor() : DomainRepository {
    companion object {
        var count = 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getAllNotes(): List<NoteModel> {
        val list: List<NoteModel>
        withContext(Dispatchers.IO) {
            when (count) {
                0 -> {
                    delay(3000)
                    list = emptyList()
                    count++
                }
                1 -> {
                    count++
                    delay(10000)
                    throw ConnectException("Lost internet connection")
                }
                2 -> {
                    delay(5000)
                    list = listOf(
                        NoteModel(id = 1, title = "note 1", subtitle = "Subtitle for note 1", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 2, title = "note 2", subtitle = "Subtitle for note 2", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 3, title = "note 3", subtitle = "Subtitle for note 3", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 4, title = "note 4", subtitle = "Subtitle for note 4", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 5, title = "note 5", subtitle = "Subtitle for note 5", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 6, title = "note 6", subtitle = "Subtitle for note 6", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 7, title = "note 6", subtitle = "Subtitle for note 6", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 8, title = "note 6", subtitle = "Subtitle for note 6", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 9, title = "note 6", subtitle = "Subtitle for note 6", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 10, title = "note 6", subtitle = "Subtitle for note 6", date = LocalDate.now(), author = "Author"),
                        NoteModel(id = 11, title = "note 6", subtitle = "Subtitle for note 6", date = LocalDate.now(), author = "Author"),
                    )
                }
                else -> {
                    list = emptyList()
                }
            }
            return@withContext list
        }
        return list
    }
}