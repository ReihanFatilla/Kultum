package com.reift.kultum.`interface`

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum

interface GetUserKultumCallBack {
    fun getUserKultum(): List<Kultum>
}