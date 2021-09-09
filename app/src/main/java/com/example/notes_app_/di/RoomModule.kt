package com.example.notes_app_.di

import android.content.Context
import androidx.room.Room
import com.example.notes_app_.NoteDao
import com.example.notes_app_.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"

        ).build()
    }

    @Provides
    fun provideDao(database: NoteDatabase): NoteDao {
        return database.getNoteDao()
    }

}
