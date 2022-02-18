package com.yash.simplenotes.database

import androidx.lifecycle.LiveData
import androidx.room.Delete

class NotesRepo(private val notesDao: NotesDAO) {
    val allData = notesDao.getData()
    suspend fun addNote(note: NoteData) {
        notesDao.insertNote(note = note)
    }

    //Function to delete all notes present
    suspend fun deleteAll() {
        notesDao.deleteAll()
    }

    //Function to update note
    suspend fun updateNote(note: NoteData) {
        notesDao.updateNote(note)
    }
    //Function to delete a particular note
    suspend fun deleteNote(note: NoteData) {
        notesDao.deleteNote(note)
    }

}