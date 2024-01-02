package com.teamsparta.todoapplication.domain.user.repository

import com.teamsparta.todoapplication.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email: String) : Boolean
    fun existsByNickname(nickname: String) : Boolean
}