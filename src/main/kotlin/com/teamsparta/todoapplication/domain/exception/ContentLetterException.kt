package com.teamsparta.todoapplication.domain.exception

data class ContentLetterException(val content: String) : RuntimeException(
    "할일은 1자~1000자 이내로 작성해 주시기 바랍니다."
)