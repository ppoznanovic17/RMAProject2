package com.example.rma2.presentation.view.states

sealed class AddClassState {

    object Success: AddClassState()
    data class Error(val message: String): AddClassState()
}