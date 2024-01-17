package com.teamsparta.todoapplication.domain.todo.dto

import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.comment.model.toResponse
import com.teamsparta.todoapplication.domain.todo.model.Todo

data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val status: String,
    val comments: List<CommentResponse>
) {
    companion object {
        fun from(todo : Todo) : TodoResponse {
            return TodoResponse(
                id = todo.id!!,
                title = todo.title,
                content = todo.content,
                status = todo.status.toString(),
                comments = todo.comments.map { it.toResponse() }
            )
        }
    }
}
