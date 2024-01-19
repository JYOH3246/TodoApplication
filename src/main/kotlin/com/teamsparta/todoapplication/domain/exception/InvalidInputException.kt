package com.teamsparta.todoapplication.domain.exception

data class InvalidInputException(val id: Long) : RuntimeException(
    "회원번호${id}가 존재하지 않는 유저입니다."
)
