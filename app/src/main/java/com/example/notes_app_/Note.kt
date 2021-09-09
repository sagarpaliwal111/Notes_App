package com.example.notes_app_

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "phone")
    var Phone: String,

    @ColumnInfo(name = "Amount")
    var Amount:String
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}

