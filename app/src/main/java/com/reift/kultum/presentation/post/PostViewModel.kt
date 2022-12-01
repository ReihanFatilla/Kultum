package com.reift.kultum.presentation.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.usecase.post.PostUseCase

class PostViewModel(
    val postUseCase: PostUseCase
): ViewModel() {

    val isVideoFound = MutableLiveData(false)

    fun postKultum(caption: String, urlKultum: String){
        postUseCase.postKultum(caption, urlKultum)
    }

}