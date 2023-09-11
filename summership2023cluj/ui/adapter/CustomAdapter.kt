package com.example.summership2023cluj.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.summership2023cluj.R
import com.example.summership2023cluj.data.dto.DetailEntryData
import com.example.summership2023cluj.data.dto.EntriesData
import com.example.summership2023cluj.data.dto.MyEntriesData
import com.example.summership2023cluj.data.dto.ProfileData
import com.example.summership2023cluj.databinding.DevicesPageRecyclerViewItemBinding
import com.example.summership2023cluj.databinding.MyDevicesPageMyEntriesItemBinding
import com.example.summership2023cluj.databinding.MyDevicesPageMyTransfersItemBinding
import com.example.summership2023cluj.ui.Navigator

internal class CustomAdapter(
    private var itemsList: ArrayList<Any>,
    private var profile: ProfileData
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_ALL_ENTRIES = 0
        private const val VIEW_TYPE_MY_TRANSFERS = 1
        private const val VIEW_TYPE_MY_ENTRIES = 2
    }

    override fun getItemViewType(position: Int): Int {
        val item = itemsList[position]
        return when (item) {
            is EntriesData -> VIEW_TYPE_ALL_ENTRIES
            is DetailEntryData -> VIEW_TYPE_MY_TRANSFERS
            is MyEntriesData -> VIEW_TYPE_MY_ENTRIES
            else -> throw IllegalArgumentException("Invalid Item Type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ALL_ENTRIES -> {
                EntriesPageViewHolder(
                    DevicesPageRecyclerViewItemBinding.bind(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.devices_page_recycler_view_item, parent, false)
                    )
                )
            }

            VIEW_TYPE_MY_TRANSFERS -> {
                MyTransfersViewHolder(
                    MyDevicesPageMyTransfersItemBinding.bind(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.my_devices_page_my_transfers_item, parent, false)
                    )
                )
            }

            VIEW_TYPE_MY_ENTRIES -> {
                MyEntriesViewHolder(
                    MyDevicesPageMyEntriesItemBinding.bind(
                        LayoutInflater.from(parent.context)
                            .inflate(R.layout.my_devices_page_my_entries_item, parent, false)
                    )
                )
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EntriesPageViewHolder -> {
                val entry = itemsList[position]
                if (entry is EntriesData)
                    holder.bind(entry)

            }

            is MyTransfersViewHolder -> {
                val entry = itemsList[position]
                if (entry is DetailEntryData)
                    holder.bind(entry)
            }

            is MyEntriesViewHolder -> {
                val entry = itemsList[position]
                if (entry is MyEntriesData)
                    holder.bind(entry)
            }
        }
    }

    inner class EntriesPageViewHolder(private var binding: DevicesPageRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val brandTextView: TextView
            get() = binding.brandTextView
        private val modelTextView: TextView
            get() = binding.modelTextView
        private val holderTextView: TextView
            get() = binding.holderTextView

        private val buttonDetailPage: ImageButton
            get() = binding.detailPageButton

        private val context: Context
            get() = binding.root.context

        fun bind(item: EntriesData) {
            buttonDetailPage.setOnClickListener {
                Navigator.getInstance().detailPageFragment(item.id)
            }
            brandTextView.text =
                binding.root.context.getString(R.string.brand_description, item.brand)
            modelTextView.text = item.model
            if (!item.isActive) {
                binding.devicesItemLayout.background = ContextCompat.getDrawable(
                    context,
                    R.drawable.inactive_recycler_view_item_design
                )
                holderTextView.text = binding.root.context.getString(R.string.inactive_description)
                holderTextView.setTextColor(Color.RED)
            } else if (profile.userID == item.userId) {
                holderTextView.text =
                    binding.root.context.getString(R.string.owned_holder_description)
                binding.devicesItemLayout.background = ContextCompat.getDrawable(
                    context,
                    R.drawable.owned_device_item_design
                )
            } else {
                if (item.userId != null)
                    holderTextView.text = context.getString(
                        R.string.holder_description,
                        item.userId.toString()
                    )
                else
                    holderTextView.text =
                        binding.root.context.getString(R.string.no_holder_description)
                binding.devicesItemLayout.background = ContextCompat.getDrawable(
                    context,
                    R.drawable.active_recycler_view_item_design
                )
            }

        }
    }

    inner class MyTransfersViewHolder(private var binding: MyDevicesPageMyTransfersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val brandTextView: TextView
            get() = binding.brandTextView
        private val modelTextView: TextView
            get() = binding.modelTextView

        fun bind(item: DetailEntryData) {
            brandTextView.text =
                binding.root.context.getString(R.string.brand_description, item.brand)
            modelTextView.text = item.model
        }
    }

    inner class MyEntriesViewHolder(private var binding: MyDevicesPageMyEntriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val brandTextView: TextView
            get() = binding.brandTextView
        private val modelTextView: TextView
            get() = binding.modelTextView

        private val buttonDetailPage: ImageButton
            get() = binding.detailPageButton

        fun bind(item: MyEntriesData) {
            buttonDetailPage.setOnClickListener {
                Navigator.getInstance().detailPageFragment(item.id)
            }

            brandTextView.text =
                binding.root.context.getString(R.string.brand_description, item.brand)
            modelTextView.text = item.model
        }
    }

}