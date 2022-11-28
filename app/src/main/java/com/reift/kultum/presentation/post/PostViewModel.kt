package com.reift.kultum.presentation.post

import androidx.lifecycle.ViewModel
import com.reift.core.domain.usecase.post.PostUseCase

class PostViewModel(
    val postUseCase: PostUseCase
): ViewModel() {
}