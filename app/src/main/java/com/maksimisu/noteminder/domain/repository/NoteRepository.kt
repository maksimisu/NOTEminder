package com.maksimisu.noteminder.domain.repository

import androidx.room.*
import com.maksimisu.noteminder.domain.model.Note

@Dao
interface NoteRepository {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note")
    suspend fun getAllNotes(): List<Note>

    @Query("SELECT * FROM note WHERE id=:noteId")
    suspend fun getNoteById(noteId: Long): Note

    @Query("SELECT * FROM note WHERE title=:noteTitle")
    suspend fun getNoteByTitle(noteTitle: String): List<Note>

    @Delete
    suspend fun deleteNote(note: Note)

}