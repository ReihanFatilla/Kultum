package com.reift.core.domain.usecase.comment

import androidx.lifecycle.LiveData
import com.reift.core.domain.model.Comments
import com.reift.core.domain.repository.CommentRepository

class CommentInteractor(
    val commentRepository: CommentRepository
): CommentUseCase {
    override fun getKultumComments(urlKultum: String): LiveData<List<Comments>> {
        return commentRepository.getKultumComments(urlKultum)
    }

    override fun addComment(comment: Comments, urlKultum: String) {
        return commentRepository.addComment(comment, urlKultum)
    }
}