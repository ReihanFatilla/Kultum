package com.reift.core.domain.usecase.home

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Comments
import com.reift.core.domain.model.Kultum
import org.w3c.dom.Comment

interface HomeUseCase {
    fun getKultumForYou(): LiveData<List<Kultum>>
    fun addHelpfulKultum(urlKultum: String)
    fun removeHelpfulKultum(urlKultum: String)
    fun addComment(comment: Comments, urlKultum: String)
}