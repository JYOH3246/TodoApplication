package com.teamsparta.todoapplication.domain.comment.model

import com.teamsparta.todoapplication.domain.todo.model.Todo
import jakarta.persistence.*

@Entity
@Table(name = "todo_comment")
class TodoComment(
    @Column(name = "comment")
    var comment: String,
    @Column(name = "name")
    var name: String,
    @Column(name = "password")
    var password: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: Todo


) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}