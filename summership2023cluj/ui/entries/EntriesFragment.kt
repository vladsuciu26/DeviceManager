package com.example.summership2023cluj.ui.entries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summership2023cluj.MyApplication
import com.example.summership2023cluj.R
import com.example.summership2023cluj.data.dto.EntriesData
import com.example.summership2023cluj.data.dto.ProfileData
import com.example.summership2023cluj.databinding.FragmentEntriesBinding
import com.example.summership2023cluj.ui.adapter.CustomAdapter
import com.example.summership2023cluj.ui.entries.viewmodel.EntriesViewModel
import kotlinx.coroutines.launch

class EntriesFragment : Fragment() {
    private lateinit var adapter: CustomAdapter
    private var _binding: FragmentEntriesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EntriesViewModel by viewModels()
    private var listOfEntries: ArrayList<EntriesData> = arrayListOf()
    private var listOfEntriesHack: ArrayList<Any> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEntriesBinding.inflate(inflater, container, false)
        val view = binding.root
        collectState()
        return view
    }

    private fun collectState() {
        lifecycleScope.launch {
            viewModel.loadEntries()
            viewModel.uiState.collect { state ->
                if (state.entriesState?.listOfEntries != null) {
                    listOfEntries = state.entriesState.listOfEntries
                    for(item in listOfEntries)
                        listOfEntriesHack.add(item)
                    setRecyclerView()
                }
            }
        }
    }

    private fun setRecyclerView() {
        lifecycleScope.launch {
            val profile: ProfileData = MyApplication.dataStore.getProfile()
            adapter = CustomAdapter(listOfEntriesHack, profile)
            binding.recyclerViewDevices.adapter = adapter
        }
    }
}