package com.teamsparta.todoapplication.domain.todocard.controller

import com.teamsparta.todoapplication.domain.todocard.dto.AddTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.ModifyTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse
import com.teamsparta.todoapplication.domain.todocard.service.TodoCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCard")
@RestController
class TodoCardController(
        private val todoService: TodoCardService
) {
    // 1. 전체 목록 조회
    @GetMapping
    fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>> {
        return status(HttpStatus.OK).body(todoService.getAllTodoCard())
    }

    // 2. 단일 작업 조회
    @GetMapping("/{todoCardId}")
    fun getTodoCard(@PathVariable todoId: Long): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoService.getTodoCardById(todoId))
    }

    // 3. 할일 작성하기
    @PostMapping
    fun addTodoCard(@RequestBody addTodoCardRequest: AddTodoCardRequest): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.CREATED).body(todoService.addTodoCard(addTodoCardRequest))
    }

    // 4. 할일 수정하기
    @PutMapping("{todoCardId}")
    fun modifyTodoCard(@PathVariable todoId: Long, @RequestBody modifyTodoCardRequest: ModifyTodoCardRequest): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoService.modifyTodoCard(todoId,modifyTodoCardRequest))
    }

    //5. 할일 삭제하기
    @DeleteMapping("{todoCardId}")
    fun deleteTodoCard(@PathVariable todoId: Long): ResponseEntity<Unit> {
        return status(HttpStatus.OK).body(todoService.deleteTodoCard(todoId))
    }
}