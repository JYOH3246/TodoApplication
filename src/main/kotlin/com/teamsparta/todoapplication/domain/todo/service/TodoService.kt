package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.GetTodoList
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse

interface TodoService {
    fun getAllTodo(request: GetTodoList) : List<TodoResponse>
    fun getTodoById(todoId: Long) : TodoResponse
    fun addTodo(request: AddTodoRequest) : TodoResponse
    fun modifyTodo(todoId: Long, request: ModifyTodoRequest) : TodoResponse
    fun deleteTodo(todoId: Long)
}
