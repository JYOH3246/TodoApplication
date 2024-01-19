package com.teamsparta.todoapplication.domain.todo.dto

data class AddTodoRequest(
    val title: String,
    val content: String,
    val status: Boolean
)

