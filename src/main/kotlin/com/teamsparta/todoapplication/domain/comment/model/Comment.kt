package com.teamsparta.todoapplication.domain.comment.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.todo.model.Todo
import com.teamsparta.todoapplication.domain.todocard.model.TodoCard
import jakarta.persistence.*

@Entity
@Table(name = "todo_comment")
class Comment(
    @Column(name = "content")
    var content: String,
    @Column(name = "name")
    var name: String,
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

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        content = content,
        name = name
    )
}
