package com.reift.core.domain.repository

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Comments

interface CommentRepository {
    fun getKultumComments(urlKultum: String): LiveData<List<Comments>>
    fun addComment(message: String, urlKultum: String)
}