package com.example.notemvvmapp.data.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun fromDateToLong(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun fromDateToLong(date: Date): Long{
        return date.time
    }
}