package com.reift.core.domain.model

data class Kultum(
    var urlKey: String,
    var helpful: List<String>,
    var comments: List<Comments>,
    var creator: User,
    var caption: String,
    var share: Int
)
