package com.example.rma2.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rma2.data.models.Note
import com.example.rma2.data.models.NoteEntity
import com.example.rma2.presentation.view.states.AddClassState
import com.example.rma2.presentation.view.states.ClassState
import io.reactivex.Completable
import io.reactivex.Observable

interface NoteContract {



    interface ViewModel {

        val notes: LiveData<List<Note>>

        fun insert(note: NoteEntity)
        fun getAll(): LiveData<List<Note>>
        fun deleteById(id: Long)
        fun getByIdAndUpdate(id: Long, title: String, content: String)
        fun getByIdAndArchiveOrUnarchive(id: Long)
        fun getNotesByTitleOrContent(string: String): LiveData<List<Note>>
        fun getByArchived(archived: Int): LiveData<List<Note>>

    }

}