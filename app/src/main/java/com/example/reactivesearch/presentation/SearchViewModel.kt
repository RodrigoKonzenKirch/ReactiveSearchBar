package com.example.reactivesearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reactivesearch.model.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState
                .map { it.query }
                .distinctUntilChanged()
                .debounce(300)
//                .filter { it.isNotBlank() }
                .collectLatest { query ->
                    try {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                        val results = userRepository.searchUsers(query)
                        _uiState.update { it.copy(results = results, isLoading = false) }
                    } catch (e: Exception) {
                        _uiState.update { it.copy(isLoading = false, error = e.message) }
                        _events.emit(UiEvent.ShowToast("Search failed: ${e.message}"))
                    }
                }
        }
    }
}
