package com.teamsparta.todoapplication.domain.user.model

import com.teamsparta.todoapplication.domain.user.dto.UserResponse
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "nickname")
    val nickname: String,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = nickname,
        email = email
    )
}