package com.teamsparta.todoapplication.domain.exception

data class IdAndPasswordNotCorrectException(val name: String, val password: String) : RuntimeException(
    "Check your name and password."
)


