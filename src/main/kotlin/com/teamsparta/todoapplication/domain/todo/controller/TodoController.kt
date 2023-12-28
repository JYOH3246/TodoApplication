package com.teamsparta.todoapplication.domain.todo.controller

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoCardResponse
import com.teamsparta.todoapplication.domain.todo.service.TodoCardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

@RequestMapping("/todo")
@RestController
class TodoController(
        private val todoService: TodoCardService
) {
    // 1. 전체 목록 조회
    @GetMapping
    fun getTodoCardList(): ResponseEntity<List<TodoCardResponse>> {
        return status(HttpStatus.OK).body(todoService.getAllTodoCard())
    }

    // 2. 단일 작업 조회
    @GetMapping("/{todoId}")
    fun getTodoCard(@PathVariable todoId: Long): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoService.getTodoCardById(todoId))
    }

    // 3. 할일 작성하기
    @PostMapping
    fun addTodoCard(@RequestBody addTodoRequest: AddTodoRequest): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.CREATED).body(todoService.addTodoCard(addTodoRequest))
    }

    // 4. 할일 수정하기
    @PutMapping("{todoId}")
    fun modifyTodoCard(@PathVariable todoId: Long, @RequestBody modifyTodoRequest: ModifyTodoRequest): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoService.modifyTodoCard(todoId,modifyTodoRequest))
    }

    //5. 할일 삭제하기
    @DeleteMapping("{todoId}")
    fun deleteTodoCard(@PathVariable todoId: Long): ResponseEntity<Unit> {
        return status(HttpStatus.OK).body(todoService.deleteTodoCard(todoId))
    }
}