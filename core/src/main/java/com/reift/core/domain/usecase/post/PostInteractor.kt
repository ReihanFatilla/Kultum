package com.reift.core.domain.usecase.post

import com.reift.core.domain.model.Kultum
import com.reift.core.domain.repository.PostRepository

class PostInteractor(
    val postRepository: PostRepository
) : PostUseCase {

    override fun postKultum(caption:String, urlKultum: String) {
        postRepository.postKultum(caption, urlKultum)
    }

}