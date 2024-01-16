package com.teamsparta.todoapplication.domain.comment.dto


data class CommentResponse(
    val id: Long,
    val content: String,
    val name: String,
)