package com.teamsparta.todoapplication.domain.todocard.controller

import com.teamsparta.todoapplication.domain.todocard.dto.AddTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.ModifyTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse
import com.teamsparta.todoapplication.domain.todocard.service.TodoCardService
import com.teamsparta.todoapplication.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards")
@RestController
class TodoCardController(
    // 생성자만 주입했어도, Bean으로 등록했기 때문에 스프링이 알아서 인스턴스화, 조립시켜 준다.
    private val todoCardService: TodoCardService
) {
    // 1. 전체 카드 목록 조회
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun getTodoCardList(
    ): ResponseEntity<List<TodoCardResponse>> {
        return status(HttpStatus.OK).body(todoCardService.getAllTodoCard())
    }

    // 2. 단일 카드 조회
    @GetMapping("/{todoCardId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
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
    @PreAuthorize("hasRole('ADMIN')")
    fun modifyTodoCard(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @RequestBody modifyTodoCardRequest: ModifyTodoCardRequest
    ): ResponseEntity<TodoCardResponse> {
        return status(HttpStatus.OK).body(todoCardService.modifyTodoCard(todoCardId, modifyTodoCardRequest))
    }

    //5. 할일 삭제하기
    @DeleteMapping("{todoCardId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteTodoCard(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long): ResponseEntity<Unit> {
        todoCardService.deleteTodoCard(todoCardId)
        return status(HttpStatus.NO_CONTENT).build()
    }
}