package com.teamsparta.todoapplication.domain.todo.controller

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.GetTodoList
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/todo")
@RestController
class TodoController {
    // 1. 전체 목록 조회
    @GetMapping
    fun getTodoList(@RequestBody getTodoList: GetTodoList): ResponseEntity<List<TodoResponse>> {
        TODO()
    }

    // 2. 단일 작업 조회
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<TodoResponse> {
        TODO()
    }

    // 3. 할일 작성하기
    @PostMapping
    fun addTodo(@RequestBody addTodoRequest: AddTodoRequest): ResponseEntity<TodoResponse> {
        TODO()
    }

    // 4. 할일 수정하기
    @PutMapping("{todoId}")
    fun modifyTodo(@PathVariable todoId: Long, @RequestBody modifyTodoRequest: ModifyTodoRequest): ResponseEntity<TodoResponse> {
        TODO()
    }

    //5. 할일 삭제하기
    @DeleteMapping("{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        TODO()
    }
}