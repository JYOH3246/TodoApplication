package com.teamsparta.todoapplication.domain.comment.repository

import com.teamsparta.todoapplication.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByTodoIdAndId(todoId: Long, commentId: Long): Comment?

}