package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoCardResponse

interface TodoCardService {
    fun getAllTodoCard() : List<TodoCardResponse>
    fun getTodoCardById(todoId: Long) : TodoCardResponse
    fun addTodoCard(request: AddTodoRequest) : TodoCardResponse
    fun modifyTodoCard(todoId: Long, request: ModifyTodoRequest) : TodoCardResponse
    fun deleteTodoCard(todoId: Long)
}
