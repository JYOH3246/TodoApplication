package com.teamsparta.todoapplication.domain.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val name: String,
    val nickname: String,
    val role: String  //ADMIN인가 MEMBER인가
)
