package com.example.summership2023cluj.ui.detail.states

import com.example.summership2023cluj.data.dto.DetailEntryData

data class EntryState(
    val entry: DetailEntryData? = null
)

data class EntryStateWrapper(
    val entryState: EntryState?
)