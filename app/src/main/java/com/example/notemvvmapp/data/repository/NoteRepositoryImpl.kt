package com.example.notemvvmapp.data.repository

import com.example.notemvvmapp.data.database.NoteDao
import com.example.notemvvmapp.data.model.Note
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
//    private val db: NoteDatabase
    private val noteDao: NoteDao
) {

    suspend fun insertNote(note: Note) = noteDao.insertNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    fun getAllNotes() = noteDao.getAllNotes()

    fun searchNote(query: String?) = noteDao.searchNote(query)

}