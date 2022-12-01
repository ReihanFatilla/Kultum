package com.reift.kultum.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.reift.core.domain.model.Comments
import com.reift.kultum.databinding.ItemCommentBinding

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    val listComment = arrayListOf<Comments>()

    fun setComments(list: List<Comments>) {
        listComment.clear()
        listComment.addAll(list)
    }

    class CommentViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CommentViewHolder(
        ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.binding.apply {
            with(listComment[position]) {

                tvUsername.text = creator
                tvMessage.text = message
                Glide.with(imgProfile.context)
                    .load(photoUrl)
                    .apply(RequestOptions())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(imgProfile)

            }
        }
    }

    override fun getItemCount() = listComment.size
}