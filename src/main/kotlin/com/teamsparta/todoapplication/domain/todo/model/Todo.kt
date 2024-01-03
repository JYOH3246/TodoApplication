package com.teamsparta.todoapplication.domain.todo.model

import com.teamsparta.todoapplication.domain.comment.model.TodoComment
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todocard.model.TodoCard
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "todo")
class Todo(
    @Column(name = "title")
    var title: String,
    @Column(name = "content")
    var content: String,
    @Column(name = "date")
    var date: Date,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todocard_id")
    val todocard: TodoCard,
    @Column(name = "status")
    var status: Boolean = false,
    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var todoComments: MutableList<TodoComment> = mutableListOf()

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addTodoComment(todoComment: TodoComment) {
        todoComments.add(todoComment)
    }

    fun removeTodoComment(todoComment: TodoComment) {
        todoComments.remove(todoComment)
    }

}

fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        title = title,
        content = content,
        date = date,
        status = status.toString()
        // Response에 댓글 리스트 추가
    )
}