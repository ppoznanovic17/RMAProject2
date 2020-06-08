package com.example.rma2.data.datasources.local

import androidx.room.*
import com.example.rma2.data.models.ClassEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class ClassDao {

    @Query("DELETE FROM class")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<ClassEntity>): Completable


    @Query("SELECT * FROM class")
    abstract fun getAll(): Observable<List<ClassEntity>>

    @Transaction
    open fun deleteAndInsertAll(entities: List<ClassEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM class WHERE nastavnik LIKE '%' || :nastavnik || '%' AND predmet LIKE '%' || :predmet || '%' AND grupe LIKE '%' || :grupa || '%' AND dan LIKE '%' || :dan || '%'")
    abstract fun getFilteredClasses(nastavnik: String, predmet: String, dan: String, grupa: String): Observable<List<ClassEntity>>


}