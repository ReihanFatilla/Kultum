package com.reift.core.domain.usecase.comment

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Comments

interface CommentUseCase {
    fun getKultumComments(urlKultum: String): LiveData<List<Comments>>
    fun addComment(message: String, urlKultum: String)
}