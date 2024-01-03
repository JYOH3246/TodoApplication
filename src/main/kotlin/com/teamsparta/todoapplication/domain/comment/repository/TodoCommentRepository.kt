package com.teamsparta.todoapplication.domain.comment.repository

import com.teamsparta.todoapplication.domain.comment.model.TodoComment
import org.springframework.data.jpa.repository.JpaRepository

interface TodoCommentRepository : JpaRepository<TodoComment, Long> {
    fun findByTodoCardIdAndTodoIdAndId(todoCardId: Long, todoId: Long, todoCommentId:Long) : TodoComment?
    fun findByNameAndPassword(name: String, password: String) : List<TodoComment>

}