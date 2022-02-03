package com.yash.simplenotes.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.yash.simplenotes.database.NoteData
import com.yash.simplenotes.database.NotesDatabase
import com.yash.simplenotes.database.NotesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    var allData: LiveData<List<NoteData>>? = null
    private val repo: NotesRepo?

    init {
        val noteDao = NotesDatabase.getDatabaseInstance(application)?.getNoteDAO()
        repo = noteDao?.let { NotesRepo(notesDao = it) }
        allData = repo?.allData
    }

    //Function to add note to the database
    fun addNote(noteData: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repo?.addNote(note = noteData)
        }
    }
    //Function to update the note
    fun updateNote(note: NoteData) {
        viewModelScope.launch(Dispatchers.IO) {
            repo?.updateNote(note = note)
        }
    }
}