package com.example.reactivesearch.presentation

sealed class UiEvent {
    data class ShowToast(val message: String) : UiEvent()
}
