package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.GetTodoList
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class TodoServiceImpl : TodoService {
    override fun getAllTodo(request: GetTodoList): List<TodoResponse> {
        TODO("Not yet implemented")
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun addTodo(request: AddTodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun modifyTodo(todoId: Long, request: ModifyTodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }
    @Transactional
    override fun deleteTodo(todoId: Long) {
        TODO("Not yet implemented")
    }
}