package com.example.reactivesearch.presentation

import com.example.reactivesearch.data.User

data class SearchUiState(
    val query: String = "",
    val results: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)