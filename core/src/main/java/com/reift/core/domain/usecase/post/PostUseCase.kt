package com.reift.core.domain.usecase.post

import com.reift.core.domain.model.Kultum

interface PostUseCase {
    fun postKultum(kultum: Kultum)
}