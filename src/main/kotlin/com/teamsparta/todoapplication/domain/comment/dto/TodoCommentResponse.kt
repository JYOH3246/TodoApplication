package com.teamsparta.todoapplication.domain.comment.dto

import java.util.*

data class TodoCommentResponse(
    val id: Long,
    val comment: String,
    val name: String,
    val date: Date
)