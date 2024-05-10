package com.example.notemvvmapp

import android.content.Context
import androidx.room.Room
import com.example.notemvvmapp.data.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


//     Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationComponent (i.e. everywhere in the application)
//    @Singleton
    @Provides
    fun provideYourDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app, NoteDatabase::class.java, "note_db"
    ).build()


//     The reason we can construct a database for the repo
//        @Singleton
    @Provides
    fun provideYourDao(db: NoteDatabase) = db.getNoteDao()


}