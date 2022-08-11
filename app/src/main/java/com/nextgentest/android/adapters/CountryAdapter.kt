package com.nextgentest.android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nextgentest.android.databinding.ItemLayoutBinding
import com.nextgentest.android.models.Country

class CountryAdapter : ListAdapter<Country,CountryAdapter.CountryHolder>(CountryDiffUtil){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        val countryItem = getItem(position)
        holder.binding.tvName.text = "${countryItem.name},${countryItem.region}"
        holder.binding.tvCode.text = countryItem.code
        holder.binding.tvCapital.text = countryItem.capital

    }

    inner class CountryHolder(val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}

val CountryDiffUtil = object : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }


}