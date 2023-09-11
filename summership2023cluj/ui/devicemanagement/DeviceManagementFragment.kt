package com.example.summership2023cluj.ui.devicemanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summership2023cluj.R
import com.example.summership2023cluj.data.dto.DevicesData
import com.example.summership2023cluj.databinding.FragmentDeviceManagementBinding
import com.example.summership2023cluj.ui.adapter.DevicesAdapter
import com.example.summership2023cluj.ui.devicemanagement.viewmodel.DeviceManagementViewModel
import kotlinx.coroutines.launch

class DeviceManagementFragment : Fragment() {
    private lateinit var adapterDevices: DevicesAdapter
    private val viewModel: DeviceManagementViewModel by viewModels()
    private var listOfDevices: ArrayList<DevicesData> = arrayListOf()

    private var _binding: FragmentDeviceManagementBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDeviceManagementBinding.inflate(inflater, container, false)
        val view = binding.root

        setupButtonForDevices(binding.buttonDevices)
        setupButtonForModels(binding.buttonModels)
        setupButtonForBrands(binding.buttonBrands)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDevices.performClick()
    }

    private fun setupButtonForDevices(button: Button) {
        button.setOnClickListener() {
            binding.buttonModels.setBackgroundColor(resources.getColor(R.color.grey_3SS))
            binding.buttonBrands.setBackgroundColor(resources.getColor(R.color.grey_3SS))
            it.setBackgroundColor(resources.getColor(R.color.red_3SS))
            lifecycleScope.launch {
                viewModel.loadDevices()
                viewModel.uiState.collect { state ->
                    if (state.devicesState?.listOfDevices != null) {
                        listOfDevices = state.devicesState.listOfDevices
                        binding.addFab.text = "ADD DEVICE"
                        setDevicesRecyclerView()
                    }
                }
            }
        }
    }

    private fun setupButtonForModels(button: Button) {
        button.setOnClickListener() {
            binding.buttonDevices.setBackgroundColor(resources.getColor(R.color.grey_3SS))
            binding.buttonBrands.setBackgroundColor(resources.getColor(R.color.grey_3SS))
            it.setBackgroundColor(resources.getColor(R.color.red_3SS))
            lifecycleScope.launch {
                viewModel.loadModels()
                viewModel.uiState.collect { state ->
                    if (state.devicesState?.listOfDevices != null) {
                        listOfDevices = state.devicesState.listOfDevices
                        binding.addFab.text = "ADD MODEL"
                        setDevicesRecyclerView()
                    }
                }
            }
        }
    }

    private fun setupButtonForBrands(button: Button) {
        button.setOnClickListener() {
            binding.buttonDevices.setBackgroundColor(resources.getColor(R.color.grey_3SS))
            binding.buttonModels.setBackgroundColor(resources.getColor(R.color.grey_3SS))
            it.setBackgroundColor(resources.getColor(R.color.red_3SS))
            lifecycleScope.launch {
                viewModel.loadBrands()
                viewModel.uiState.collect { state ->
                    if (state.devicesState?.listOfDevices != null) {
                        listOfDevices = state.devicesState.listOfDevices
                        binding.addFab.text = "ADD BRAND"
                        setDevicesRecyclerView()
                    }
                }
            }
        }
    }

    private fun setDevicesRecyclerView() {
        lifecycleScope.launch {
            adapterDevices = DevicesAdapter(listOfDevices)
            binding.recyclerView.adapter = adapterDevices
            val layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerView.layoutManager = layoutManager
        }
    }

}