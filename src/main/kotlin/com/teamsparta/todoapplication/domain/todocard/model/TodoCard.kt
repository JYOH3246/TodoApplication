package com.teamsparta.todoapplication.domain.todocard.model

import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "todocard")
class TodoCard(
        @Column(name="name")
        val name: String,
        @Column(name="date")
        val date: Date,

        )

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    // 1. 할일 추가하기

}
fun TodoCard.toResponse () : TodoCardResponse {
    return TodoCardResponse(
            id = id!!,
            date = date,
            name = name

    )
}