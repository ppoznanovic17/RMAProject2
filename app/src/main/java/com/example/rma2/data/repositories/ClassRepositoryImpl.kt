package com.example.rma2.data.repositories

import com.example.rma2.data.datasources.local.ClassDao
import com.example.rma2.data.datasources.remote.ClassService
import com.example.rma2.data.models.Class
import com.example.rma2.data.models.ClassEntity
import com.example.rma2.data.models.Resource
import io.reactivex.Observable
import timber.log.Timber

class ClassRepositoryImpl (private val localDataSource: ClassDao,
                           private val remoteDataSource: ClassService) : ClassRepository {
    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext {
                val entities = it.map {
                    ClassEntity(
                        0,
                        it.predmet,
                        it.tip,
                        it.nastavnik,
                        it.grupe,
                        it.dan,
                        it.termin,
                        it.ucionica
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }

    }

    override fun getAll(): Observable<List<Class>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    Class(
                        it.id,
                        it.predmet,
                        it.tip,
                        it.nastavnik,
                        it.grupe,
                        it.dan,
                        it.termin,
                        it.ucionica)
                }
            }
    }

    override fun getFilteredClasses(
        nastavnik: String,
        predmet: String,
        dan: String,
        grupa: String
    ): Observable<List<Class>> {
        return localDataSource.getFilteredClasses(nastavnik,predmet, dan, grupa)
            .map {
                it.map {
                    Class(it.id, it.predmet, it.tip, it.nastavnik, it.grupe, it.dan, it.termin, it.ucionica)
                }
            }
    }


}