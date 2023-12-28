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
        private val todoCardService: TodoCardService
) {
    // 1. 전체 카드 목록 조회
    @GetMapping
    fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>> {
        return status(HttpStatus.OK).body(todoCardService.getAllTodoCard())
    }

    // 2. 단일 카드 조회
    @GetMapping("/{todoCardId}")
    fun getTodoCard(@PathVariable todoCardId: Long): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoCardService.getTodoCardById(todoCardId))
    }

    // 3. 할일카드 작성하기
    @PostMapping
    fun addTodoCard(@RequestBody addTodoCardRequest: AddTodoCardRequest): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.CREATED).body(todoCardService.addTodoCard(addTodoCardRequest))
    }

    // 4. 할일카드 수정하기
    @PutMapping("{todoCardId}")
    fun modifyTodoCard(@PathVariable todoCardId: Long, @RequestBody modifyTodoCardRequest: ModifyTodoCardRequest): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoCardService.modifyTodoCard(todoCardId,modifyTodoCardRequest))
    }

    //5. 할일 삭제하기
    @DeleteMapping("{todoCardId}")
    fun deleteTodoCard(@PathVariable todoCardId: Long): ResponseEntity<Unit> {
        return status(HttpStatus.NO_CONTENT).build()
    }
}