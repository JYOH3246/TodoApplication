package com.teamsparta.todoapplication.domain.todocard.repository

import com.teamsparta.todoapplication.domain.todocard.model.TodoCard
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCardRepository : JpaRepository<TodoCard, Long> {
}