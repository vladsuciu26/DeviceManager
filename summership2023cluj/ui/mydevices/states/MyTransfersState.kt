package com.example.summership2023cluj.ui.mydevices.states

import com.example.summership2023cluj.data.dto.HolderData
import com.example.summership2023cluj.data.dto.TransferData

data class MyTransfersState(
    val listOfMyTransfers: ArrayList<TransferData>? = null
)


data class MyTransfersStateWrapper(
    val myTransfersState: MyTransfersState?
)
