package com.maksimisu.noteminder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maksimisu.noteminder.domain.model.Note
import com.maksimisu.noteminder.domain.repository.NoteRepository

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteRepository
}