package com.example.rma2.data.repositories

import com.example.rma2.data.models.Note
import com.example.rma2.data.models.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface NoteRepository {
    fun insert(note: NoteEntity): Completable
    fun getAll(): Observable<List<Note>>
    fun deleteById(id: Long): Completable
    fun getByIdAndUpdate(id: Long, title: String, content: String): Completable
    fun getByIdAndArchiveOrUnarchive(id: Long): Completable
    fun getNotesByTitleOrContent(string: String): Observable<List<Note>>
    fun getByArchived(archived: Int): Observable<List<Note>>
}