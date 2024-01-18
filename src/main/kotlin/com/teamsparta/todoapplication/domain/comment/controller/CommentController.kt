package com.teamsparta.todoapplication.domain.comment.controller

import com.teamsparta.todoapplication.domain.comment.dto.AddCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.comment.dto.ModifyCommentRequest
import com.teamsparta.todoapplication.domain.comment.service.CommentService
import com.teamsparta.todoapplication.infra.security.jwt.UserPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCards/{todoCardId}/todos/{todoId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {
    //1. 목록 조회하기
    @GetMapping
    fun getComment(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long
    ): ResponseEntity<List<CommentResponse>> {
        return status(HttpStatus.OK).body(commentService.getComment(todoId))
    }

    @PostMapping
    fun addComment(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @RequestBody addCommentRequest: AddCommentRequest
    ): ResponseEntity<CommentResponse> {
        return status(HttpStatus.CREATED).body(
            commentService.addComment(
                todoId,
                addCommentRequest
            )
        )
    }

    @PutMapping("/{commentId}")
    fun modifyComment(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody modifyCommentRequest: ModifyCommentRequest
    ): ResponseEntity<CommentResponse> {
        return status(HttpStatus.OK).body(
            commentService.modifyComment(
                todoId, commentId, modifyCommentRequest
            )
        )
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
    ): ResponseEntity<Any> {
        commentService.deleteComment(todoId, commentId)
        return status(HttpStatus.OK).body("선택하신 댓글이 삭제되었습니다.")
    }


}