package com.example.rma2.data.repositories

import com.example.rma2.data.datasources.local.NoteDao
import com.example.rma2.data.models.Note
import com.example.rma2.data.models.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable

class NoteRepositoryImpl (private val noteDao: NoteDao) : NoteRepository{

    override fun insert(note: NoteEntity): Completable {
        return noteDao.insertNote(note)
    }

    override fun getAll(): Observable<List<Note>> {
        return noteDao.getAll()
            .map {
                it.map {
                    Note(
                        it.id,
                        it.title,
                        it.content,
                        it.archived
                    )
                }
            }
    }

    override fun deleteById(id: Long):Completable {
        return noteDao.deleteById(id)
    }

    override fun getByIdAndUpdate(id: Long, title: String, content: String) :Completable{
        return noteDao.getByIdAndUpdate(id, title, content)
    }

    override fun getByIdAndArchiveOrUnarchive(id: Long) :Completable{
        return noteDao.getByIdAndArchiveOrUnarchive(id)
    }

    override fun getNotesByTitleOrContent(string: String): Observable<List<Note>> {
        return noteDao.getNotesByTitleOrContent(string).map {
            it.map {
                Note(
                    it.id,
                    it.title,
                    it.content,
                    it.archived
                )
            }
        }
    }

    override fun getByArchived(archived: Int): Observable<List<Note>> {
        return noteDao.getByArchived(archived).map {
            it.map {
                Note(
                    it.id,
                    it.title,
                    it.content,
                    it.archived
                )
            }
        }
    }

}