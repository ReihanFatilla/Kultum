package com.reift.kultum.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reift.kultum.R
import com.reift.kultum.databinding.ItemKultumBinding

class KultumAdapter:RecyclerView.Adapter<KultumAdapter.KultumViewHolder>() {

    private var listKultum = arrayListOf<Int>()

    fun setKultum(list: List<Int>){
        listKultum.clear()
        listKultum.addAll(list)
    }

    class KultumViewHolder(val binding: ItemKultumBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KultumViewHolder(
        ItemKultumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: KultumViewHolder, position: Int) {
        holder.binding.root.setImageResource(listKultum[position])
    }

    override fun getItemCount() = listKultum.size
}