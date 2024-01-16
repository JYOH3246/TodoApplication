package com.teamsparta.todoapplication.domain.todo.controller

import com.teamsparta.todoapplication.domain.todo.dto.*
import com.teamsparta.todoapplication.domain.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
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
        @RequestParam getTodoRequest: GetTodoRequest,
    ): ResponseEntity<List<TodoResponseForAll>> {
        return status(200).body(todoService.getAllTodo(todoCardId, getTodoRequest))
    }

    // 2. 단일 작업 조회 + 댓글 목록 조회하기
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoCardId: Long, @PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        return status(200).body(todoService.getTodoById(todoCardId, todoId))
    }

    // 3. 할일 작성하기
    @PostMapping
    fun addTodo(
        @PathVariable todoCardId: Long,
        @RequestBody addTodoRequest: AddTodoRequest
    ): ResponseEntity<TodoResponse> {
        return status(201).body(todoService.addTodo(todoCardId, addTodoRequest))
    }

    // 4. 할일 수정하기
    @PutMapping("/{todoId}")
    fun modifyTodo(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @RequestBody modifyTodoRequset: ModifyTodoRequest
    ): ResponseEntity<TodoResponse> {
        return status(200).body(todoService.modifyTodo(todoCardId, todoId, modifyTodoRequset))
    }

    // 5. 할일 삭제하기
    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoCardId: Long, @PathVariable todoId: Long)
            : ResponseEntity<Unit> {
        todoService.deleteTodo(todoCardId,todoId)
        return status(204).build()
    }
}