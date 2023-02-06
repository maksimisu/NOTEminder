package com.maksimisu.noteminder.data.local

import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteRepository: NoteRepository
) {

    suspend fun insertNote(note: Note) = noteRepository.insertNote(note)
    suspend fun getAllNotes(): List<Note> = noteRepository.getAllNotes()
    suspend fun getNoteById(noteId: Long): Note = noteRepository.getNoteById(noteId)
    suspend fun getNoteByTitle(noteTitle: String): List<Note> = noteRepository.getNoteByTitle(noteTitle)
    suspend fun deleteNote(note: Note) = noteRepository.deleteNote(note)

}