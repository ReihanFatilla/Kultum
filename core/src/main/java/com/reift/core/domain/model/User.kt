package com.reift.core.domain.model

data class User(
    var name: String = "",
    var username: String= "",
    var email: String= "",
    var photoUrl: String= "https://i.ibb.co/JnjLb55/profile-picture-temp.jpg",
    var bio: String= "",
    var password: String= "",
    var kultumAmount: String = "0",
    var followers: List<Follow> = listOf(),
    var follwings: List<Follow> = listOf()
)
