package com.teamsparta.todoapplication.domain.user.controller

import com.teamsparta.todoapplication.domain.user.dto.SignUpRequest
import com.teamsparta.todoapplication.domain.user.dto.UserResponse
import com.teamsparta.todoapplication.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {
    /*
    1. 회원가입
    DB : request를 받은 이메일 패스워드 이름 별명을 저장하고 이를 response로 변환해 return
    예외사항 : 이메일 중복 / 별명 중복
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signUpRequest))
    }
}