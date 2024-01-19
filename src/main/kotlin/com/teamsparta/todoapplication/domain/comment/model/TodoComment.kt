package com.teamsparta.todoapplication.domain.comment.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.teamsparta.todoapplication.domain.comment.dto.TodoCommentResponse
import com.teamsparta.todoapplication.domain.todo.model.Todo
import com.teamsparta.todoapplication.domain.todocard.model.TodoCard
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "todo_comment")
class TodoComment(
    @Column(name = "comment")
    var comment: String,
    @Column(name = "name")
    var name: String,
    @Column(name = "date")
    var date: Date,
    @JsonIgnore
    @Column(name = "password")
    val password: String,
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todocard_id")
    private val todoCard: TodoCard


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun TodoComment.toResponse(): TodoCommentResponse {
    return TodoCommentResponse(
        id = id!!,
        comment = comment,
        date = date,
        name = name
    )
}
