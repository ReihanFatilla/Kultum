package com.reift.core.domain.model

data class User(
    var name: String = "",
    var usernname: String= "",
    var email: String= "",
    var photoUrl: String= "",
    var bio: String= "",
    var password: String= "",
    var kultum: List<Kultum> = listOf(),
    var followers: List<Follow> = listOf(),
    var follwings: List<Follow> = listOf(),
)
