package com.yash.simplenotes.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {
    //Insertion function for the app
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: NoteData)

    //To get and display the data on the app
    @Query("SELECT * FROM NotesTable")
    fun getData(): LiveData<List<NoteData>>

    //Deleting all from the table
    @Query("DELETE FROM NotesTable")
    fun deleteAll()

    //Function to delete all notes from the database
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateNote(note: NoteData)

    //Function to delete a particular note
    @Delete
    suspend fun deleteNote(note: NoteData)

    //Function to search things
    @Query("SELECT * FROM notestable WHERE title LIKE :searchQuery or note LIKE :searchQuery")
    fun searchNotes(searchQuery: String): LiveData<List<NoteData>>
}