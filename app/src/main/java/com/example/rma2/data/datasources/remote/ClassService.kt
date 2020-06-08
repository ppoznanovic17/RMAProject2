package com.example.rma2.data.datasources.remote

import com.example.rma2.data.models.ClassResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ClassService {

    @GET(".")
    fun getAll(): Observable<List<ClassResponse>>

}