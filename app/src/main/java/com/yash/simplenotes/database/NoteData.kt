package com.yash.simplenotes.database

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize
import java.util.*
@Entity(tableName = "NotesTable")
data class NoteData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "Title") var title: String,
    @ColumnInfo(name = "Note") var note: String,
    @Nullable @ColumnInfo(name = "Date") var date: String? = null
)