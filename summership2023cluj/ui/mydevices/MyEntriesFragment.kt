package com.example.summership2023cluj.ui.mydevices

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summership2023cluj.MyApplication
import com.example.summership2023cluj.data.dto.MyEntriesData
import com.example.summership2023cluj.data.dto.ProfileData
import com.example.summership2023cluj.data.dto.TransferData
import com.example.summership2023cluj.databinding.FragmentMyEntriesBinding
import com.example.summership2023cluj.ui.adapter.CustomAdapter
import com.example.summership2023cluj.ui.mydevices.viewmodel.MyEntriesViewModel
import kotlinx.coroutines.launch


class MyEntriesFragment : Fragment() {
    private lateinit var adapter: CustomAdapter
    private var _binding: FragmentMyEntriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MyEntriesViewModel by viewModels()
    private var listOfMyEntries: ArrayList<MyEntriesData> = arrayListOf()
    private var listOfMyTransfers: ArrayList<TransferData> = arrayListOf()
    private var listOfAllEntries: ArrayList<Any> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMyEntriesBinding.inflate(inflater, container, false)
        val view = binding.root
        collectStateOfMyTransfers()
        collectStateOfMyEntries()
        return view
    }

    private fun collectStateOfMyTransfers() {
        lifecycleScope.launch {
            viewModel.loadMyTransfers()
            viewModel.transfersUiState.collect { state ->
                if (state.myTransfersState?.listOfMyTransfers != null) {
                    listOfMyTransfers = state.myTransfersState.listOfMyTransfers
                    for (transfer in listOfMyTransfers) {
                        val id = transfer.currentHolder.entryId
                        val entry = viewModel.loadEntry(id)
                        listOfAllEntries.add(entry!!)
                    }
                }
            }
        }
    }

    private fun collectStateOfMyEntries() {
        lifecycleScope.launch {
            viewModel.loadMyEntries()
            viewModel.uiState.collect { state ->
                if (state.myEntriesState?.listOfMyEntries != null) {
                    listOfMyEntries = state.myEntriesState.listOfMyEntries
                    for (entry in listOfMyEntries)
                        listOfAllEntries.add(entry)
                }
                setRecyclerView()
            }
        }
    }

    private fun setRecyclerView() {
        lifecycleScope.launch {
            val profile: ProfileData = MyApplication.dataStore.getProfile()
            adapter = CustomAdapter(listOfAllEntries, profile)
            binding.recyclerViewDevices.adapter = adapter
            val layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewDevices.layoutManager = layoutManager
        }
    }


}