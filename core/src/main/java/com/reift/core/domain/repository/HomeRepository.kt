package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Kultum
import org.w3c.dom.Comment

interface HomeRepository {
    fun getKultumForYou(): LiveData<List<Kultum>>
    fun addHelpfulKultum(kultum: Kultum)
    fun removeHelpfulKultum(kultum: Kultum)
    fun addComment(comment: Comment)
}