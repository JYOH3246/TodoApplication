package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class ModifyTodoRequset (
        val title :String,
        val content : String,
        val date : Date
)