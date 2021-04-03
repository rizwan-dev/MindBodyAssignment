package com.riz.mindbody.ui.province

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.riz.mindbody.data.entities.Province
import com.riz.mindbody.databinding.ItemProvinceListBinding

class ProvinceListAdapter(private val onItemClick : (Province) -> Unit) : ListAdapter<Province, ProvinceListAdapter.ViewHolder>(ProvinceItemDiffCallback) {

    class ViewHolder(private val binding: ItemProvinceListBinding, onItemClick: (Province) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        lateinit var province: Province

        init {
            binding.root.setOnClickListener {
                onItemClick.invoke(province)
            }
        }

        fun bindData(province: Province){
            this.province = province
            binding.tvName.text = province.Name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProvinceListBinding.inflate(LayoutInflater.from(parent.context), parent,false), onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(getItem(position))
    }

}

object ProvinceItemDiffCallback : DiffUtil.ItemCallback<Province>() {
    override fun areItemsTheSame(oldItem: Province, newItem: Province) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Province, newItem: Province) = oldItem.ID == newItem.ID

}