package com.teamsparta.todoapplication.domain.comment.dto

import java.util.*

data class AddCommentRequest(
    val content: String,
    val name: String,
    val date: Date,
    val password: String
)