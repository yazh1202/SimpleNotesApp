package com.yash.simplenotes.database

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "NotesTable")
data class NoteData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "Title") var title: String,
    @ColumnInfo(name = "Note") var note: String,
    @Nullable @ColumnInfo(name = "Date") var date: String? = null
) : Parcelable