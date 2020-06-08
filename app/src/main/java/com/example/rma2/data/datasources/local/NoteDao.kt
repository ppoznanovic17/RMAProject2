package com.example.rma2.data.datasources.local

import androidx.room.*
import com.example.rma2.data.models.ClassEntity
import com.example.rma2.data.models.NoteEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class NoteDao {

    @Query("DELETE FROM note")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<NoteEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNote(note: NoteEntity): Completable

    @Query("SELECT * FROM note WHERE id == :id")
    abstract fun getById(id: Long): NoteEntity

    @Query("SELECT * FROM note")
    abstract fun getAll(): Observable<List<NoteEntity>>

    @Transaction
    open fun deleteAndInsertAll(entities: List<NoteEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("DELETE FROM note WHERE id == :id")
    abstract fun deleteById(id: Long): Completable

    @Update
    abstract fun update(studentEntity: NoteEntity): Completable


    open fun getByIdAndUpdate(id: Long, title: String, content: String): Completable {
        val note = getById(id)
        val updatedNote = note.copy(
            title = title,
            content = content
        )
         return update(updatedNote)
    }

    open fun getByIdAndArchiveOrUnarchive(id: Long): Completable {
        val note = getById(id)
        val archived = note.archived
        var update = -1
        update = if(archived == 1) 0
                else 1
        val updatedNote = note.copy(
            archived = update
        )
        return update(updatedNote)
    }

    @Query("SELECT * FROM note WHERE title LIKE '%' || :string || '%' OR content LIKE '%' || :string || '%'")
    abstract fun getNotesByTitleOrContent(string: String): Observable<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE archived == :archived")
    abstract fun getByArchived(archived: Int): Observable<List<NoteEntity>>

}