package com.teamsparta.todoapplication.domain.todo.dto

data class TodoResponseForAll(
    val id: Long,
    val title: String,
    val content: String,
    val status: String,

    )
