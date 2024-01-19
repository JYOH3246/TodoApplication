package com.teamsparta.todoapplication.domain.user.service

import com.teamsparta.todoapplication.domain.user.dto.LoginRequest
import com.teamsparta.todoapplication.domain.user.dto.LoginResponse
import com.teamsparta.todoapplication.domain.user.dto.SignUpRequest
import com.teamsparta.todoapplication.domain.user.dto.UserResponse

interface UserService {
    fun signUp(request: SignUpRequest): UserResponse
    fun login(request: LoginRequest): LoginResponse
    fun searchMyInfo(id: Long): UserResponse

}