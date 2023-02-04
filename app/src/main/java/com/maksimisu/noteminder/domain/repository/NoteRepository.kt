package com.maksimisu.noteminder.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.maksimisu.noteminder.domain.model.Note

@Dao
interface NoteRepository {

    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id=:noteId")
    suspend fun getNoteById(noteId: Long)

    @Query("SELECT * FROM note WHERE title=:noteTitle")
    suspend fun getNoteByTitle(noteTitle: String)

    @Delete
    suspend fun deleteNote(note: Note)

}