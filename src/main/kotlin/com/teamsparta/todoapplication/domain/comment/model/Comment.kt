package com.teamsparta.todoapplication.domain.comment.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.todo.model.Todo
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comment(
    @Column(name = "content")
    var content: String,
    @Column(name = "name")
    var name: String,
    @Column(name = "password")
    val password: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "todo_id")
    val todo: Todo

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
