package com.example.summership2023cluj.ui.devicemanagement.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.summership2023cluj.data.repositories.BrandsRepository
import com.example.summership2023cluj.data.repositories.DevicesRepository
import com.example.summership2023cluj.data.repositories.ModelsRepository
import com.example.summership2023cluj.ui.devicemanagement.states.DevicesState
import com.example.summership2023cluj.ui.devicemanagement.states.DevicesStateWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeviceManagementViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DevicesStateWrapper(null))
    val uiState: StateFlow<DevicesStateWrapper> = _uiState.asStateFlow()
    private val devicesRepository: DevicesRepository = DevicesRepository()
    private val modelsRepository: ModelsRepository = ModelsRepository()
    private val brandsRepository: BrandsRepository = BrandsRepository()


    fun loadDevices() {
        viewModelScope.launch(Dispatchers.IO) {
            val devicesResponse = devicesRepository.fetchDevicesResponse()
            if (devicesResponse.body()?.success == true) {
                _uiState.update { currentState ->
                    currentState.copy(
                        devicesState = DevicesState(devicesResponse.body()?.data)
                    )
                }
            }
        }
    }

    fun loadModels() {
        viewModelScope.launch(Dispatchers.IO) {
            val modelResponse = modelsRepository.fetchModelsResponse()
            if (modelResponse.body()?.success == true) {
                _uiState.update { currentState ->
                    currentState.copy(
                        devicesState = DevicesState(modelResponse.body()?.data)
                    )
                }
            }
        }
    }

    fun loadBrands() {
        viewModelScope.launch(Dispatchers.IO) {
            val brandsResponse = brandsRepository.fetchBrandsResponse()
            if (brandsResponse.body()?.success == true) {
                _uiState.update { currentState ->
                    currentState.copy(
                        devicesState = DevicesState(brandsResponse.body()?.data)
                    )
                }
            }
        }
    }
}