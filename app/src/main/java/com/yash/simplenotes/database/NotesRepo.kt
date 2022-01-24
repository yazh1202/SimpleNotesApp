package com.yash.simplenotes.database

class NotesRepo(private val notesDao: NotesDAO) {
    val allData = notesDao.getData()
    suspend fun addNote(note: NoteData) {
        notesDao.insertNote(note = note)
    }

    fun deleteAll() {
        notesDao.deleteAll()
    }
}