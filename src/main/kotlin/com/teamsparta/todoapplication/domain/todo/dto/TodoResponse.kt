package com.teamsparta.todoapplication.domain.todo.dto

import com.teamsparta.todoapplication.domain.comment.model.Comment

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val status: String,
    val comments: MutableList<Comment>
)