package com.example.notemvvmapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notemvvmapp.data.model.Note

@Database(entities = [Note::class], version = 1)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

//    companion object {
//
//        // @Volatile Means if it value changes all the thread will get its updated value
//        @Volatile
//        private var instance: NoteDatabase? = null
//
//        private val LOCk = Any()
//
//        // one line function
//        // need of invoke
//        // simplicity creating instance
//        // and double check locking pattern to ensure thread safety during database creation process
//        operator fun invoke(context: Context) =
//        // return instance if not null
//        //or create new in synchronized block
//            // so that only one thread can create instance
//            instance ?: synchronized(LOCk) {
//                instance ?: createDatabase(context).also {
//                    instance = it
//                }
//            }
//
//        // one line function
//        // responsible for creating noteDatabase
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                NoteDatabase::class.java,
//                "note_db"
//            ).build()
//
//    }
}