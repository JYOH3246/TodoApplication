package com.teamsparta.todoapplication.domain.todo.dto

import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val status: String,
    val comments: List<CommentResponse>
)
