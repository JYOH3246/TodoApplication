package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class ModifyTodoRequest(
        val name: String,
        val date : Date
)
