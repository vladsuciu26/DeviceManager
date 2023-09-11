package com.example.summership2023cluj.ui.entries.states

import com.example.summership2023cluj.data.dto.EntriesData

data class EntriesState(
    val listOfEntries: ArrayList<EntriesData>? = null
)

data class EntriesStateWrapper(
    val entriesState: EntriesState?
)
