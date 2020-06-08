package com.example.rma2.data.repositories

import com.example.rma2.data.models.Class
import com.example.rma2.data.models.Resource
import io.reactivex.Observable

interface ClassRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Class>>
    fun getFilteredClasses(nastavnik: String, predmet: String, dan: String, grupa: String): Observable<List<Class>>

}