package com.teamsparta.todoapplication.domain.user.service

import com.teamsparta.todoapplication.domain.user.dto.SignUpRequest
import com.teamsparta.todoapplication.domain.user.dto.UserResponse

interface UserService {
    fun signUp(signUpRequest: SignUpRequest) : UserResponse
}