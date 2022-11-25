package com.reift.core.domain.model

data class Kultum(
    var urlKey: String,
    var helpful: List<String>,
    var comments: List<Comments>,
    var creator: String,
    var caption: String,
    var share: Int
)
