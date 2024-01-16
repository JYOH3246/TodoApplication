package com.teamsparta.todoapplication.domain.todocard.model

import com.teamsparta.todoapplication.domain.BaseTimeEntity
import com.teamsparta.todoapplication.domain.todo.model.Todo
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse
import jakarta.persistence.*

@Entity
@Table(name = "todocard")
class TodoCard(
    @Column(name = "name")
    var name: String,
    @OneToMany(mappedBy = "todocard", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var todos: MutableList<Todo> = mutableListOf()

): BaseTimeEntity()  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun addTodo(todo: Todo) {
        todos.add(todo)
    }

    fun removeTodo(todo: Todo) {
        todos.remove(todo)
    }

}

fun TodoCard.toResponse(): TodoCardResponse {
    return TodoCardResponse(
        id = id!!,
        name = name,
    )
}