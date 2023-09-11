package com.example.summership2023cluj.ui.mydevices.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summership2023cluj.data.dto.DetailEntryData
import com.example.summership2023cluj.data.repositories.EntriesRepository
import com.example.summership2023cluj.data.repositories.EntryRepository
import com.example.summership2023cluj.data.repositories.MyEntriesRepository
import com.example.summership2023cluj.data.repositories.TransfersRepository
import com.example.summership2023cluj.ui.mydevices.states.MyEntriesState
import com.example.summership2023cluj.ui.mydevices.states.MyEntriesStateWrapper
import com.example.summership2023cluj.ui.mydevices.states.MyTransfersState
import com.example.summership2023cluj.ui.mydevices.states.MyTransfersStateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyEntriesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyEntriesStateWrapper(null))
    val uiState: StateFlow<MyEntriesStateWrapper> = _uiState.asStateFlow()

    private val _transfersUiState = MutableStateFlow(MyTransfersStateWrapper(null))
    val transfersUiState: StateFlow<MyTransfersStateWrapper> = _transfersUiState.asStateFlow()
    private val myTransfersRepo: TransfersRepository = TransfersRepository()
    private val entryRepo: EntryRepository = EntryRepository()
    private val myEntriesRepo: MyEntriesRepository = MyEntriesRepository()

    fun loadMyEntries() {
        viewModelScope.launch(Dispatchers.IO) {
            val entriesResponse = myEntriesRepo.fetchMyEntriesResponse()
            if (entriesResponse.body()?.success == true) {
                _uiState.update { currentState ->
                    currentState.copy(
                        myEntriesState = MyEntriesState(entriesResponse.body()?.data)
                    )
                }
            }
        }
    }

    fun loadMyTransfers() {
        viewModelScope.launch(Dispatchers.IO) {
            val transfersResponse = myTransfersRepo.fetchTransfers()
            if (transfersResponse.body()?.success == true) {
                _transfersUiState.update { currentState ->
                    currentState.copy(
                        myTransfersState = MyTransfersState(transfersResponse.body()?.data)
                    )
                }
            }
        }
    }

    suspend fun loadEntry(idOfEntry: Int): DetailEntryData? {
        val entry = entryRepo.fetchEntryResponse(idOfEntry).body()?.data
        return entryRepo.fetchEntryResponse(idOfEntry).body()?.data
    }
}