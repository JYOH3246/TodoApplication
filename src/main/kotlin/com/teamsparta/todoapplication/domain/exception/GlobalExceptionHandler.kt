package com.teamsparta.todoapplication.domain.exception

import com.teamsparta.todoapplication.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice// 전역적으로 예외처리할 수 있는 어노테이션 @RestControllerAdivce
class GlobalExceptionHandler { // 이 클래스는 전역적으로 예외를 핸들링할 수 있는 클래스임

    @ExceptionHandler(ModelNotFoundException::class) // 어떤 클래스로 처리할 것인가
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        //404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(IdAndPasswordNotCorrectException::class)
    fun handleIdAndPasswordNotCorrectException(e: IdAndPasswordNotCorrectException): ResponseEntity<ErrorResponse> {
        //400 Bad Request
        return ResponseEntity.status(400).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(TitleLetterLengthException::class)
    fun handleTitleLetterLengthException(e: TitleLetterLengthException): ResponseEntity<ErrorResponse> {
        //400 Bad Request
        return ResponseEntity.status(400).body(ErrorResponse(message = e.message))
    }

    @ExceptionHandler(ContentLetterException::class)
    fun handleContentLetterException(e: ContentLetterException): ResponseEntity<ErrorResponse> {
        //400 Bad Request
        return ResponseEntity.status(400).body(ErrorResponse(message = e.message))
    }
}