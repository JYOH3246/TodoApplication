package com.teamsparta.todoapplication.domain.todocard.service

import com.teamsparta.todoapplication.domain.todocard.dto.AddTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.ModifyTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse

interface TodoCardService {
    fun getAllTodoCard(): List<TodoCardResponse>
    fun getTodoCardById(todoCardId: Long): TodoCardResponse
    fun addTodoCard(request: AddTodoCardRequest): TodoCardResponse
    fun modifyTodoCard(todoCardId: Long, request: ModifyTodoCardRequest): TodoCardResponse
    fun deleteTodoCard(todoCardId: Long)
}
