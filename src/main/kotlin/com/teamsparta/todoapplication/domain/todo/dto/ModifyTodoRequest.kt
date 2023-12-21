package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class ModifyTodoRequest(
        val title :String,
        val description :String,
        val name :String
)
