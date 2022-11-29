package com.reift.kultum.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.reift.core.domain.model.Kultum
import com.reift.kultum.`interface`.OnItemClickCallBack
import com.reift.kultum.databinding.ItemKultumBinding
import com.reift.kultum.utils.ThumbnailUrlFormatter

class KultumAdapter:RecyclerView.Adapter<KultumAdapter.KultumViewHolder>() {

    private var listKultum = arrayListOf<Kultum>()

    var onItemClickCallBack: OnItemClickCallBack? = null

    fun setKultum(list: List<Kultum>){
        listKultum.clear()
        listKultum.addAll(list)
    }

    fun setItemClickCallback(onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack
    }

    class KultumViewHolder(val binding: ItemKultumBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KultumViewHolder(
        ItemKultumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: KultumViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(root.context)
                .load(ThumbnailUrlFormatter.format(listKultum[position].urlKey))
                .apply(RequestOptions())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(root)

            root.setOnClickListener{
                onItemClickCallBack?.onClick(position)
            }
        }
    }

    override fun getItemCount() = listKultum.size
}