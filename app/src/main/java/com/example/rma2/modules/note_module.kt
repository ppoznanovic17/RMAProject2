package com.example.rma2.modules


import com.example.rma2.data.datasources.local.NoteDao
import com.example.rma2.data.datasources.local.NoteDataBase
import com.example.rma2.data.repositories.NoteRepository
import com.example.rma2.data.repositories.NoteRepositoryImpl
import com.example.rma2.presentation.viewmodel.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteModule = module {


    viewModel { NoteViewModel ( noteRepository = get()) }

    single<NoteRepository> { NoteRepositoryImpl(noteDao = get()) }

    single { get<NoteDataBase>().getNoteDao() }



}