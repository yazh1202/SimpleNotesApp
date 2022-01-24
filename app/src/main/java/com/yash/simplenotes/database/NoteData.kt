package com.yash.simplenotes.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NotesTable")
data class NoteData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "Title") var title: String,
    @ColumnInfo(name = "Note") var note: String
)