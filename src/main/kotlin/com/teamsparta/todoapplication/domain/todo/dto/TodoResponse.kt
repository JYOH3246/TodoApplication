package com.teamsparta.todoapplication.domain.todo.dto

import java.util.*

data class TodoResponse (
        val id : Long,
        val title :String,
        val content : String,
        val date : Date
)