package com.reift.core.domain.repository

import com.reift.core.domain.model.Kultum

interface PostRepository {
    fun postKultum(kultum: Kultum)
}