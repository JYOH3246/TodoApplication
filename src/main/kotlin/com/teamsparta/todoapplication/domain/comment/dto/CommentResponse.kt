package com.teamsparta.todoapplication.domain.comment.dto

import java.util.*

data class CommentResponse(
    val id: Long,
    val content: String,
    val name: String,
    val date: Date
)