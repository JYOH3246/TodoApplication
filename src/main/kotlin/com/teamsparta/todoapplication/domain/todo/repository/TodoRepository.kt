package com.teamsparta.todoapplication.domain.todo.repository

import com.teamsparta.todoapplication.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByTodocardIdAndId(todoCardId: Long, todoId: Long): Todo?
}