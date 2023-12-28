package com.teamsparta.todoapplication.domain.todocard.service

import com.teamsparta.todoapplication.domain.todocard.dto.AddTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.ModifyTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse

interface TodoCardService {
    fun getAllTodoCard() : List<TodoCardResponse>
    fun getTodoCardById(todoId: Long) : TodoCardResponse
    fun addTodoCard(request: AddTodoCardRequest) : TodoCardResponse
    fun modifyTodoCard(todoId: Long, request: ModifyTodoCardRequest) : TodoCardResponse
    fun deleteTodoCard(todoId: Long)
}
