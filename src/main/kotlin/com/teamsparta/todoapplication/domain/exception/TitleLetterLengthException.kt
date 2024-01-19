package com.teamsparta.todoapplication.domain.exception

data class TitleLetterLengthException(val title: String) : RuntimeException(
    "제목은 1자~200자 이내로 작성해 주시기 바랍니다."
)
