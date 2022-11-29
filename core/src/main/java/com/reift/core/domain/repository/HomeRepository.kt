package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Comments
import com.reift.core.domain.model.Kultum
import org.w3c.dom.Comment

interface HomeRepository {
    fun getKultumForYou(): LiveData<List<Kultum>>
    fun getKultumDetail(urlKultum: String): LiveData<Kultum>
    fun isKultumHelpfuled(urlKultum: String): LiveData<Boolean>
    fun addHelpfulKultum(urlKultum: String)
    fun removeHelpfulKultum(urlKultum: String)
    fun addComment(comment: Comments, urlKultum: String)
}