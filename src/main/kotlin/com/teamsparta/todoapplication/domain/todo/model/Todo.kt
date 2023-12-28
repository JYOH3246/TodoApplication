package com.teamsparta.todoapplication.domain.todo.model

import com.teamsparta.todoapplication.domain.todo.dto.TodoCardResponse
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "todocard")
class Todo (
        @Column(name="name")
        val name : String,
        @Column(name="date")
        val date : LocalDate,


)

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // 1. 할일 추가하기

}
fun Todo.toResponse () : TodoCardResponse {
    return TodoCardResponse(
            id = id!!,
            date = date,
            name = name

    )
}