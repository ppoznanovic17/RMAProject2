package com.example.rma2.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rma2.data.datasources.local.converter.DateConverter
import com.example.rma2.data.models.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
}