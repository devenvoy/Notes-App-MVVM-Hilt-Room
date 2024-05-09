package com.example.notemvvmapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity(tableName = "notes")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var noteTitle: String,
    @ColumnInfo(name = "description")
    var noteDescription: String,
    @ColumnInfo(name = "createdAt")
    var createDate: Date,
    @ColumnInfo(name = "modifyAt")
    var modifyDate: Date
) : Parcelable