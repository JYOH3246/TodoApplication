package com.teamsparta.todoapplication.domain.todo.repository

import com.teamsparta.todoapplication.domain.todo.model.Todo

interface CustomTodoRepository {


    fun findByTodoWithComments(todoCardId: Long, todoId: Long): List<Todo>
}