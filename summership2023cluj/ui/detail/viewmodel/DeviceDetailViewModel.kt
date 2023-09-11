package com.example.summership2023cluj.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summership2023cluj.data.repositories.EntryRepository
import com.example.summership2023cluj.ui.detail.states.EntryState
import com.example.summership2023cluj.ui.detail.states.EntryStateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeviceDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EntryStateWrapper(null))
    val uiState: StateFlow<EntryStateWrapper> = _uiState.asStateFlow()
    private val repo: EntryRepository = EntryRepository()

    fun loadDetailEntry(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val entryResponse = repo.fetchEntryResponse(id)
            if (entryResponse.body()?.success == true) {
                _uiState.update { currentState ->
                    currentState.copy(
                        entryState = EntryState(entryResponse.body()?.data)
                    )
                }
            }
        }
    }
}