package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponseForAll


interface TodoService {
    fun getAllTodo(todoCardId: Long): List<TodoResponseForAll>
    fun getTodo(todoCardId: Long, todoId: Long): TodoResponse
    fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponseForAll
    fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequest): TodoResponse
    fun deleteTodo(todoCardId: Long, todoId: Long)
    fun getTodoWithComments(todoCardId: Long, todoId: Long): List<TodoResponse>

}