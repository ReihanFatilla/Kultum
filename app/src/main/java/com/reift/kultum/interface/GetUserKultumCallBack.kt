package com.reift.kultum.`interface`

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.reift.core.domain.model.Kultum
import com.reift.kultum.adapter.recyclerview.KultumAdapter

interface GetUserKultumCallBack {
    fun getUserKultum(rvKultum: RecyclerView, username: String)
}