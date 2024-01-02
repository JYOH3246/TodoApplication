package com.teamsparta.todoapplication.domain.comment.service

import com.teamsparta.todoapplication.domain.comment.dto.AddTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.DeleteTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.TodoCommentResponse
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequset

interface TodoCommentService {
    fun getTodoComment(todoId: Long): List<TodoCommentResponse>
    fun addTodoComment(todoCardId: Long, todoId: Long, request: AddTodoCommentRequest): TodoCommentResponse
    fun modifyTodo(todoCardId: Long, todoId: Long, todoCommentId: Long, request: ModifyTodoRequset): TodoCommentResponse
    fun deleteTodo(todoCardId: Long, todoId: Long, todoCommentId: Long, request: DeleteTodoCommentRequest)
}