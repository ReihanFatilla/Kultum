package com.reift.core.domain.model

data class User(
    var name: String,
    var usernname: String,
    var email: String,
    var photoUrl: String,
    var bio: String,
    var kultum: List<Kultum>,
    var followers: List<User>,
    var follwings: List<User>,
)
