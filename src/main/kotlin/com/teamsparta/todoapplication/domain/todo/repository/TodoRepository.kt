package com.teamsparta.todoapplication.domain.todo.repository

import com.teamsparta.todoapplication.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>, CustomTodoRepository {
    fun findBytodoCardIdAndId(todoCardId: Long, todoId: Long): Todo?
}