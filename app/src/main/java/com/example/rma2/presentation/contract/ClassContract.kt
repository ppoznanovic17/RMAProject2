package com.example.rma2.presentation.contract

import androidx.lifecycle.LiveData
import com.example.rma2.presentation.view.states.AddClassState
import com.example.rma2.presentation.view.states.ClassState

interface ClassContract {

    interface ViewModel {

        val classState: LiveData<ClassState>
        val addDone: LiveData<AddClassState>

        fun fetchAllClasses()
        fun getAllClasses()
        fun getFilteredClasses(nastavnik: String, predmet: String, dan: String, grupa: String )

    }

}