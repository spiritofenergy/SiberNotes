package com.example.siberianotes.domain.model

data class UserModel(
    var userId: String? = null,
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val about: String?,
)
