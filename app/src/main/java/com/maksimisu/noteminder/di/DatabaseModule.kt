package com.maksimisu.noteminder.di

import android.content.Context
import androidx.room.Room
import com.maksimisu.noteminder.data.local.AppDatabase
import com.maksimisu.noteminder.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideAppData(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "notes_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(appDatabase: AppDatabase): NoteRepository {
        return appDatabase.noteDao()
    }

}