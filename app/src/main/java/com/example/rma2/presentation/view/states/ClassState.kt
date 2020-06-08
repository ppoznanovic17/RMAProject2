package com.example.rma2.presentation.view.states

import com.example.rma2.data.models.Class

sealed class ClassState {

    object Loading: ClassState()
    object DataFetched: ClassState()
    data class Success(val classes: List<Class>): ClassState()
    data class Error(val message: String): ClassState()
}