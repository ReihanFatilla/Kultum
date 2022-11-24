package com.reift.core.domain.model

data class Kultum(
    var urlKey: String,
    var helpful: Int,
    var comments: List<Comments>,
    var creator: User,
    var caption: String
)
