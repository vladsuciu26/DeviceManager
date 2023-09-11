package com.example.summership2023cluj.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.summership2023cluj.R
import com.example.summership2023cluj.data.dto.DevicesData
import com.example.summership2023cluj.databinding.DeviceManagementItemBinding

internal class DevicesAdapter(
    private var itemsList: ArrayList<DevicesData>
) :
    RecyclerView.Adapter<DevicesAdapter.DevicesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder =
        DevicesViewHolder(
            DeviceManagementItemBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.device_management_item, parent, false)
            )
        )

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        val device = itemsList[position]
        holder.bind(device)
    }

    inner class DevicesViewHolder(private var binding: DeviceManagementItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val idDeviceManagementTextView: TextView
            get() = binding.idDeviceManagementTextView
        private val nameDeviceManagementTextView: TextView
            get() = binding.nameDeviceManagementTextView

        fun bind(item: DevicesData) {
            idDeviceManagementTextView.text =
                binding.root.context.getString(R.string.id_description, item.id.toString())
            nameDeviceManagementTextView.text = item.name
        }
    }
}