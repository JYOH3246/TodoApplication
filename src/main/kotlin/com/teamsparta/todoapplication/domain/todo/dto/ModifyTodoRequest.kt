package com.teamsparta.todoapplication.domain.todo.dto

data class ModifyTodoRequest(
    val title: String,
    val content: String,
    val status: Boolean
)