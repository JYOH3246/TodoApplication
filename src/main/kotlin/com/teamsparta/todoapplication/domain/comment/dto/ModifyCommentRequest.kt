package com.teamsparta.todoapplication.domain.comment.dto

data class ModifyCommentRequest(
    val content: String,
    val name: String,
    val password: String
)