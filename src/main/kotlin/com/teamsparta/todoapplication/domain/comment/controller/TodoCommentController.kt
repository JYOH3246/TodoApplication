package com.teamsparta.todoapplication.domain.comment.controller

import com.teamsparta.todoapplication.domain.comment.dto.AddTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.TodoCommentResponse
import com.teamsparta.todoapplication.domain.comment.service.TodoCommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCard/{todoCardId}/todo/{todoId}/comment")
@RestController
class TodoCommentController(
    private val todoCommentService: TodoCommentService
) {
    //1. 목록 조회하기
    @GetMapping
    fun getTodoComment(
        @PathVariable todoId: Long,
        @PathVariable todoCardId: String
    ): ResponseEntity<List<TodoCommentResponse>> {
        return status(HttpStatus.CREATED)
            .body(todoCommentService.getTodoComment(todoId))
    }

    @PostMapping
    fun addTodoComment(
        @PathVariable todoId: Long,
        @PathVariable todoCardId: Long,
        @RequestBody addTodoCommentRequest: AddTodoCommentRequest
    ): ResponseEntity<TodoCommentResponse> {
        return status(HttpStatus.CREATED)
            .body(todoCommentService.addTodoComment(todoCardId, todoId, addTodoCommentRequest))

    }
}