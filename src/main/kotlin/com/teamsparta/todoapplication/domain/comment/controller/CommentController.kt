package com.teamsparta.todoapplication.domain.comment.controller

import com.teamsparta.todoapplication.domain.comment.dto.AddCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.ModifyCommentRequest
import com.teamsparta.todoapplication.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.*

@RequestMapping("/todoCard/{todoCardId}/todo/{todoId}/comment")
@RestController
class CommentController(
    private val commentService: CommentService
) {
    //1. 목록 조회하기
    @GetMapping
    fun getComment(
        @PathVariable todoCardId: String,@PathVariable todoId: Long
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
                todoCardId,
                todoId,
                addCommentRequest
            )
        )
    }

    @PutMapping("/{commentId}")
    fun modifyComment(
        @PathVariable todoCardId: Long,
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody modifyCommentRequest: ModifyCommentRequest
    ): ResponseEntity<CommentResponse> {
        return status(200).body(
            commentService.modifyComment(
                todoCardId, todoId, commentId, modifyCommentRequest
            )
        )
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoCardId: Long, @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody deleteCommentRequest: DeleteCommentRequest
    ): ResponseEntity<Any> {
        commentService.deleteComment(todoCardId, todoId, commentId, deleteCommentRequest)
        return status(HttpStatus.OK).body("선택하신 댓글이 삭제되었습니다.")
    }


}