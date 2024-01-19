package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.*


interface TodoService {
    fun getAllTodo(todoCardId: Long, request: GetTodoRequest): List<TodoResponseForAll>
    fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse
    fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponse
    fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequest): TodoResponse
    fun deleteTodo(todoCardId: Long, todoId: Long)
}