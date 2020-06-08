package com.example.rma2.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "class")
data class ClassEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var predmet: String,
    var tip: String,
    var nastavnik: String,
    var grupe: String,
    var dan: String,
    var termin: String,
    var ucionica: String
    )