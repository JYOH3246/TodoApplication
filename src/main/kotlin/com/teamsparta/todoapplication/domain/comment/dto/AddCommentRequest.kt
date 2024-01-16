package com.teamsparta.todoapplication.domain.comment.dto

data class AddCommentRequest(
    val content: String,
    val name: String,
    val password: String
)