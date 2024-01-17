package com.teamsparta.todoapplication.domain.todo.controller

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponseForAll
import com.teamsparta.todoapplication.domain.todo.service.TodoService
import com.teamsparta.todoapplication.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCard/{todoCardId}/todo")
@RestController
class TodoController(
    private val todoService: TodoService
) {
    // 1. 전체 목록 조회
    @GetMapping
    fun getTodoList(
        @PathVariable todoCardId: Long,
    ): ResponseEntity<List<TodoResponseForAll>> {
        return status(HttpStatus.OK).body(todoService.getAllTodo(todoCardId))
    }

    // 2. 단일 작업 조회 + 댓글 목록 조회하기
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoCardId: Long, @PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return status(HttpStatus.OK).body(todoService.getTodoById(todoCardId, todoId))
    }

    // 3. 할일 작성하기
    @PostMapping
    fun addTodo(
        @PathVariable todoCardId: Long,
        @RequestBody addTodoRequest: AddTodoRequest
    ): ResponseEntity<TodoResponseForAll> {
        return status(HttpStatus.CREATED).body(todoService.addTodo(todoCardId, addTodoRequest))
    }

    // 4. 할일 수정하기
    @PutMapping("/{todoId}")
    fun modifyTodo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @RequestBody modifyTodoRequest: ModifyTodoRequest
    ): ResponseEntity<TodoResponseForAll> {
        return status(HttpStatus.OK).body(todoService.modifyTodo(todoCardId, todoId, modifyTodoRequest))
    }

    // 5. 할일 삭제하기
    @DeleteMapping("/{todoId}")
    fun deleteTodo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long)
            : ResponseEntity<Unit> {
        todoService.deleteTodo(todoCardId,todoId)
        return status(HttpStatus.NO_CONTENT).build()
    }
}