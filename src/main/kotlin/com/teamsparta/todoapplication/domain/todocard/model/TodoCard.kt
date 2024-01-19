package com.teamsparta.todoapplication.domain.todocard.model

import com.teamsparta.todoapplication.domain.exception.ContentLetterException
import com.teamsparta.todoapplication.domain.exception.TitleLetterLengthException
import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.model.Todo
import com.teamsparta.todoapplication.domain.todocard.dto.ModifyTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse
import com.teamsparta.todoapplication.infra.jpaaudit.BaseUserEntity
import jakarta.persistence.*

@Entity
@Table(name = "todocards")
class TodoCard(
    @Column(name = "name")
    var name: String,
    @OneToMany(mappedBy = "todoCard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var todos: MutableList<Todo> = mutableListOf()

) : BaseUserEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addTodo(todo: Todo, request: AddTodoRequest) {
        if (request.title.length in 1..200) {
            if (request.content.length in 1..1000) {
                todos.add(todo)
            } else {
                throw ContentLetterException(request.content)
            }
        } else {
            throw TitleLetterLengthException(request.title)
        }
    }

    fun removeTodo(todo: Todo) {
        todos.remove(todo)
    }

    fun modifyTodoCard(request: ModifyTodoCardRequest) {
        // (name, date) 정의하기 : ModifyTodoCardRequest 내 변수들로 정의
        // 요청받은 값으로 현재값을 교체하기
        name = request.name
    }

}

fun TodoCard.toResponse(): TodoCardResponse {
    return TodoCardResponse(
        id = id!!,
        name = name,
    )
}



