package com.reift.kultum.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.kultum.`interface`.GetUserKultumCallBack
import com.reift.kultum.`interface`.OnItemClickCallBack
import com.reift.kultum.`interface`.OnUserClickCallBack
import com.reift.kultum.databinding.ItemConnectUserBinding

class UserConnectAdapter: RecyclerView.Adapter<UserConnectAdapter.UserViewHolder>() {

    val listUser = arrayListOf<User>()

    var getUserKultumCallBack: GetUserKultumCallBack? = null

    var onUserClickCallBack: OnUserClickCallBack? = null

    fun setItemClickCallback(onUserClickCallBack: OnUserClickCallBack){
        this.onUserClickCallBack = onUserClickCallBack
    }

    fun setUserList(list: List<User>){
        listUser.clear()
        listUser.addAll(list)
    }

    fun setUserKultumCallBack(getUserKultumCallBack: GetUserKultumCallBack){
        this.getUserKultumCallBack = getUserKultumCallBack
    }

    class UserViewHolder(val binding: ItemConnectUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        ItemConnectUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            with(listUser[position]){
                tvName.text = name
                tvUsername.text = username
                Glide.with(imgProfile.context)
                    .load(photoUrl)
                    .apply(RequestOptions())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(imgProfile)

                getUserKultumCallBack?.getUserKultum(holder.binding.rvKultum, username)
                root.setOnClickListener{
                    onUserClickCallBack?.onUserClicked(username)
                }
            }
        }
    }

    override fun getItemCount() = listUser.size
}