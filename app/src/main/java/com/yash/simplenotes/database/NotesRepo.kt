package com.yash.simplenotes.database

import androidx.lifecycle.LiveData

class NotesRepo(private val notesDao: NotesDAO) {
    val allData = notesDao.getData()
    suspend fun addNote(note: NoteData) {
        notesDao.insertNote(note = note)
    }

    fun deleteAll() {
        notesDao.deleteAll()
    }

    //
//    suspend fun getNote(id: Int): LiveData<NoteData> {
//        return notesDao.getNote(id)
//    }
    suspend fun updateNote(note: NoteData) {
        notesDao.updateNote(note)
    }
}