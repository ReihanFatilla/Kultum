package com.reift.core.domain.model

data class Comments(
    var creator: User,
    var message: String,
    var date: String
)
