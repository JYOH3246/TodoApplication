package com.teamsparta.todoapplication.domain.todo.repository

import com.teamsparta.todoapplication.domain.comment.model.QComment
import com.teamsparta.todoapplication.domain.todo.model.QTodo
import com.teamsparta.todoapplication.domain.todo.model.Todo
import com.teamsparta.todoapplication.domain.todocard.model.QTodoCard
import com.teamsparta.todoapplication.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl : QueryDslSupport(), CustomTodoRepository {
    private val todo = QTodo.todo


    override fun findByTodoWithComments(todoCardId: Long, todoId: Long): List<Todo> {
        val comment = QComment.comment
        val todoCard = QTodoCard.todoCard
        val contents = queryFactory.selectFrom(todo)
            .leftJoin(todo.comments, comment)
            .fetchJoin()
            .where((todo.id.eq(todoId)), (todoCard.id.eq(todoCardId)))
            .fetchJoin()
            .fetch()
        return contents
    }
}