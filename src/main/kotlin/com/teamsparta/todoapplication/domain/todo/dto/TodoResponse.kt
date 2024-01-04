package com.teamsparta.todoapplication.domain.todo.dto

import com.teamsparta.todoapplication.domain.comment.model.TodoComment
import java.util.*

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val date: Date,
    val status: String,
    val todoComments: MutableList<TodoComment>
)