package com.teamsparta.todoapplication.domain.todo.repository

import com.teamsparta.todoapplication.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findBytodocardIdAndId(todoCardId: Long, todoId: Long): Todo?
    fun findAllByOrderByDateAsc(): MutableList<Todo>
    fun findAllByOrderByDateDesc(): MutableList<Todo>
}