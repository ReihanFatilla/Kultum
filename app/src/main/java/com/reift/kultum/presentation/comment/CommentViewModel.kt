package com.reift.kultum.presentation.comment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.model.Comments
import com.reift.core.domain.usecase.comment.CommentUseCase

class CommentViewModel(
    val commentUseCase: CommentUseCase
): ViewModel() {

    fun getKultumComments(urlKultum: String): LiveData<List<Comments>>{
        return commentUseCase.getKultumComments(urlKultum)
    }

    fun addComment(comment: Comments, urlKultum: String){
        commentUseCase.addComment(comment, urlKultum)
    }

}