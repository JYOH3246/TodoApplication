package com.teamsparta.todoapplication.domain.todo.dto

import com.teamsparta.todoapplication.domain.comment.model.Comment
import java.util.*

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: Date,
    val status: String,
    val comments: MutableList<Comment>
)