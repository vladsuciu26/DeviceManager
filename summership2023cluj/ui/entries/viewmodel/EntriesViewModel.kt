package com.example.summership2023cluj.ui.entries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summership2023cluj.data.repositories.EntriesRepository
import com.example.summership2023cluj.ui.entries.states.EntriesState
import com.example.summership2023cluj.ui.entries.states.EntriesStateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EntriesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EntriesStateWrapper(null))
    val uiState: StateFlow<EntriesStateWrapper> = _uiState.asStateFlow()
    private val repo: EntriesRepository = EntriesRepository()

    fun loadEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            val entriesResponse = repo.fetchEntriesResponse()
            if (entriesResponse.body()?.success == true) {
                _uiState.update { currentState ->
                    currentState.copy(
                        entriesState = EntriesState(entriesResponse.body()?.data)
                    )
                }
            }
        }
    }
}