package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class TodoResponseForAll(
    val id: Long,
    val title: String,
    val content: String,
    val date: Date,
    val status: String,
)
