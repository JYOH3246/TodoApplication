package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class AddTodoRequest(
        val name: String,
        val date : Date
)
