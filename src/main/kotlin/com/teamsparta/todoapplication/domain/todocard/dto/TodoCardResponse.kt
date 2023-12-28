package com.teamsparta.todoapplication.domain.todocard.dto

import java.time.LocalDate

data class TodoCardResponse(
        val id: Long,
        val name : String,
        val date :LocalDate,

)
