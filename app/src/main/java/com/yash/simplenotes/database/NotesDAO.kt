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
    suspend fun deleteAll()

    //Selecting a particular note
//     @Query("SELECT * FROM NotesTable WHERE id IS (:id) ")
//     suspend fun getNote(id:Int):LiveData<NoteData>
    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateNote(note: NoteData)
}