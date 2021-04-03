package com.riz.mindbody.ui.country.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.riz.mindbody.data.entities.Country
import com.riz.mindbody.databinding.ItemCountryListBinding

class CountryListAdapter(private val onItemClick : (Country) -> Unit) : ListAdapter<Country, CountryListAdapter.ViewHolder>(
    UserItemDiffCallback
) {

    class ViewHolder(
        private val binding: ItemCountryListBinding,
        onItemClick: (Country) -> Unit,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        lateinit var country: Country

        init {
            binding.root.setOnClickListener {
                onItemClick.invoke(country)
            }
        }

        @OptIn(ExperimentalStdlibApi::class)
        fun bindData(country: Country){
            this.country = country
            binding.tvName.text = country.Name

            Glide.with(context).load("https://github.com/hampusborgos/country-flags/blob/main/png250px/${country.Code.lowercase()}.png").into(binding.ivFlag)

            // There is some problem with image URLs, it is not getting loaded
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCountryListBinding.inflate(LayoutInflater.from(parent.context), parent,false), onItemClick, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindData(getItem(position))
    }

}

object UserItemDiffCallback : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem.ID == newItem.ID
}