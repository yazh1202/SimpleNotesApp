package com.yash.simplenotes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteData::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNoteDAO(): NotesDAO

    //To implement singleton pattern for the database access point
    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null
        fun getDatabaseInstance(context: Context): NotesDatabase? {
            if (INSTANCE != null) {
                return INSTANCE
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "NotesTable"
                ).build()
                INSTANCE = instance
                return INSTANCE
            }

        }
    }
}