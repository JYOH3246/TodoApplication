package com.teamsparta.todoapplication.domain.todo.model

import com.teamsparta.todoapplication.domain.comment.model.Comment
import com.teamsparta.todoapplication.domain.comment.model.toResponse
import com.teamsparta.todoapplication.domain.exception.ContentLetterException
import com.teamsparta.todoapplication.domain.exception.TitleLetterLengthException
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponseForAll
import com.teamsparta.todoapplication.domain.todocard.model.TodoCard
import com.teamsparta.todoapplication.infra.jpaaudit.BaseUserEntity
import jakarta.persistence.*

@Entity
@Table(name = "todos")
class Todo(
    @Column(name = "title")
    var title: String,
    @Column(name = "content")
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todoCard_id")
    val todoCard: TodoCard,
    @Column(name = "status")
    var status: Boolean = false,
    @OneToMany(mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()

): BaseUserEntity()  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }
    fun modifyTodo(request: ModifyTodoRequest) {
        if (request.title.length in 1..200) {
            if (request.content.length in 1..1000) {
                title = request.title
                content = request.content
                status = request.status
            } else {
                throw ContentLetterException(request.content)
            }
        } else {
            throw TitleLetterLengthException(request.title)
        }
    }

}
fun Todo.toResponse(): TodoResponse {
    return TodoResponse(
        id = id!!,
        title = title,
        content = content,
        status = status.toString(),
        comments = comments.map{it.toResponse()}
    )
}



fun Todo.toResponseForAll(): TodoResponseForAll {
    return TodoResponseForAll(
        id = id!!,
        title = title,
        content = content,
        status = status.toString()
    )
}

