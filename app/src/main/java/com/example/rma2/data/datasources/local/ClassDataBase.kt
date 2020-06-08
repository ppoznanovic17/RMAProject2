package com.example.rma2.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rma2.data.models.ClassEntity

@Database(
    entities = [ClassEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters()
abstract class ClassDataBase : RoomDatabase() {
    abstract fun getClassDao(): ClassDao
}