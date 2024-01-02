package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class AddTodoRequest(
    val title: String,
    val content: String,
    val date: Date,
    val status : Boolean
)

