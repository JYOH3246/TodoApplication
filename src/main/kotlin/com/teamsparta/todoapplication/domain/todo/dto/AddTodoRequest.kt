package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class AddTodoRequest(
        val title :String,
        val description :String,
        val date : Date,
        val name :String
)
