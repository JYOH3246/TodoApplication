package com.teamsparta.todoapplication.domain.comment.dto

import java.util.*

data class ModifyTodoCommentRequest(
    val comment: String,
    val name: String,
    val date: Date,
    val password: String
)