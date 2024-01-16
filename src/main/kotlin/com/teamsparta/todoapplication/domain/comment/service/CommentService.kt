package com.teamsparta.todoapplication.domain.comment.service

import com.teamsparta.todoapplication.domain.comment.dto.AddCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.ModifyCommentRequest


interface CommentService {
    fun getComment(todoId: Long): List<CommentResponse>
    fun addComment(todoCardId: Long, todoId: Long, request: AddCommentRequest): CommentResponse
    fun modifyComment(
        todoCardId: Long,
        todoId: Long,
        commentId: Long,
        request: ModifyCommentRequest
    ): CommentResponse

    fun deleteComment(todoCardId: Long, todoId: Long, commentId: Long, request: DeleteCommentRequest)
}