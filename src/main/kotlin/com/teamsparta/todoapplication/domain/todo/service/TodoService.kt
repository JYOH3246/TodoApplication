package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.GetTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequset
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse


interface TodoService {
    fun getAllTodo(todoCardId: Long, request: GetTodoRequest): List<TodoResponse>
    fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse
    fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponse
    fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequset): TodoResponse
    fun deleteTodo(todoCardId: Long, todoId: Long)
}