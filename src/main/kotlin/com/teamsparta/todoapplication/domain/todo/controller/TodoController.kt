package com.teamsparta.todoapplication.domain.todo.controller

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponseForAll
import com.teamsparta.todoapplication.domain.todo.service.TodoService
import com.teamsparta.todoapplication.domain.todocard.service.TodoCardService
import com.teamsparta.todoapplication.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards/{todoCardId}/todos")
@RestController
class TodoController(
    private val todoService: TodoService,
    private val todoCardService: TodoCardService
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
    fun getTodo(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    ): ResponseEntity<List<TodoResponse>> {
        return status(HttpStatus.OK).body(todoService.getTodoWithComments(todoCardId, todoId))
    }


    // 3. 할일 작성하기
    @PostMapping
    fun addTodo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @RequestBody addTodoRequest: AddTodoRequest
    ): ResponseEntity<TodoResponseForAll> {
        val todoCard = todoCardService.getTodoCardById(todoCardId)
        if (todoCard.email == userPrincipal.email) {
            return status(HttpStatus.CREATED).body(todoService.addTodo(todoCardId, addTodoRequest))
        } else throw IllegalStateException("접근 불가")
    }

    // 4. 할일 수정하기
    @PutMapping("/{todoId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun modifyTodo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @RequestBody modifyTodoRequest: ModifyTodoRequest
    ): ResponseEntity<TodoResponse> {
        val todoCard = todoCardService.getTodoCardById(todoCardId)
        val todo = todoService.getTodo(todoCardId, todoId)
        if (todo.email == userPrincipal.email) {
            return status(HttpStatus.OK).body(todoService.modifyTodo(todoCardId, todoId, modifyTodoRequest))
        } else throw IllegalStateException("접근 불가")
    }

    // 5. 할일 삭제하기
    @DeleteMapping("/{todoId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun deleteTodo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    )
            : ResponseEntity<Unit> {
        val todo = todoService.getTodo(todoCardId, todoId)
        if (todo.email == userPrincipal.email) {
            todoService.deleteTodo(todoCardId, todoId)
            return status(HttpStatus.NO_CONTENT).build()
        } else throw IllegalStateException("접근 불가")
    }
}
