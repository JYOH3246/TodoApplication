package com.teamsparta.todoapplication.domain.user.dto

data class LoginRequest(
    val email : String,
    val password : String,
    val role : String
)
