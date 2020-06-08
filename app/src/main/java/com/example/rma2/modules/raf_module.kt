package com.example.rma2.modules

import com.example.rma2.data.datasources.local.ClassDao
import com.example.rma2.data.datasources.local.ClassDataBase
import com.example.rma2.data.datasources.remote.ClassService
import com.example.rma2.data.repositories.ClassRepository
import com.example.rma2.data.repositories.ClassRepositoryImpl
import com.example.rma2.presentation.viewmodel.ClassViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val rafModule = module {


    viewModel { ClassViewModel ( classRepository = get()) }

    single<ClassRepository> { ClassRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single<ClassDao> { get<ClassDataBase>().getClassDao() }

    single<ClassService> { create(retrofit = get())}


}