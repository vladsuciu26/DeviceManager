package com.example.summership2023cluj.ui.mydevices.states

import com.example.summership2023cluj.data.dto.MyEntriesData

data class MyEntriesState(
    val listOfMyEntries: ArrayList<MyEntriesData>? = null
)

data class MyEntriesStateWrapper(
    val myEntriesState: MyEntriesState?
)
