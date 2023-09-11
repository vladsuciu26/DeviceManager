package com.example.summership2023cluj.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.summership2023cluj.data.Constants
import com.example.summership2023cluj.data.dto.DetailEntryData
import com.example.summership2023cluj.databinding.FragmentDetailBinding
import com.example.summership2023cluj.ui.Navigator
import com.example.summership2023cluj.ui.detail.viewmodel.DeviceDetailViewModel
import kotlinx.coroutines.launch


class DetailFragment : Fragment() {
    private val viewModel: DeviceDetailViewModel by viewModels()
    private lateinit var entryData: DetailEntryData
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        setupBackToDevicesPageButton()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: Int? = arguments?.getInt(Constants.KEY_ENTRY_ID)
        if (id != null) {
            collectState(id)
        }
    }

    private fun setupBackToDevicesPageButton() {
        binding.backToDevicesPageButton.setOnClickListener {
            lifecycleScope.launch {
                Navigator.getInstance().openDevicesFragment()
            }
        }
    }

    private fun collectState(id: Int) {
        lifecycleScope.launch {
            viewModel.loadDetailEntry(id)
            viewModel.uiState.collect { state ->
                if (state.entryState?.entry != null) {
                    entryData = state.entryState.entry
                    binding.deviceBrand.text = "Brand: ${state.entryState.entry.brand}"
                    binding.deviceModel.text = "Model: ${state.entryState.entry.model}"
                    binding.deviceSpecificationsName.text =
                        "PC Name: ${state.entryState.entry.pcName}"
                    binding.deviceSpecificationsSerialNumber.text =
                        "Serial Number: ${state.entryState.entry.serialNumber}"
                    binding.deviceSpecificationsCpu.text = "CPU: ${state.entryState.entry.cpu}"
                    binding.deviceSpecificationsRam.text = "RAM: ${state.entryState.entry.ram}"
                    binding.deviceSpecificationsHdd.text = "HDD: ${state.entryState.entry.hdd}"
                    binding.deviceOtherDetailsProject.text =
                        "Project: ${state.entryState.entry.project}"
                    binding.deviceOtherDetailsManufactureDate.text =
                        "Manufacture Date: ${state.entryState.entry.manufactureDate}"
                    binding.deviceOtherDetailsComment.text =
                        "Comment: ${state.entryState.entry.comment}"
                }
            }
        }
    }
}