package com.example.notemvvmapp.data.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notemvvmapp.data.model.Note
import com.example.notemvvmapp.data.repository.NoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepositoryImpl: NoteRepositoryImpl
) : ViewModel() {

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepositoryImpl.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepositoryImpl.deleteNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepositoryImpl.updateNote(note)
    }

    fun getAllNotes() = noteRepositoryImpl.getAllNotes()

    fun searchNote(query: String?) = noteRepositoryImpl.searchNote(query)
}