package com.teamsparta.todoapplication.domain.todocard.repository

import com.teamsparta.todoapplication.domain.todocard.model.TodoCard
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository : JpaRepository<TodoCard, Long> {
    fun findAllByOrderByDateAsc(): MutableList<TodoCard>
    fun findAllByOrderByDateDesc(): MutableList<TodoCard>
    fun findByNameOrderByDateAsc(name: String): MutableList<TodoCard>
    fun findByNameOrderByDateDesc(name: String): MutableList<TodoCard>
    fun findByName(name: String): MutableList<TodoCard>
}