package com.example.rma2.data.models

import java.util.*

data class Note(
    var id: Long,
    var title: String,
    var content: String,
    var archives: Int
)