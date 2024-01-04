package com.teamsparta.todoapplication.domain.comment.service

import com.teamsparta.todoapplication.domain.comment.dto.AddTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.DeleteTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.ModifyTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.TodoCommentResponse


interface TodoCommentService {
    fun getTodoComment(todoId: Long): List<TodoCommentResponse>
    fun addTodoComment(todoCardId: Long, todoId: Long, request: AddTodoCommentRequest): TodoCommentResponse
    fun modifyTodoComment(
        todoCardId: Long,
        todoId: Long,
        todoCommentId: Long,
        request: ModifyTodoCommentRequest
    ): TodoCommentResponse

    fun deleteTodoComment(todoCardId: Long, todoId: Long, todoCommentId: Long, request: DeleteTodoCommentRequest)
}