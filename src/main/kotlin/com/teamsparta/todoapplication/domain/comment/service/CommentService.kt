package com.teamsparta.todoapplication.domain.comment.service

import com.teamsparta.todoapplication.domain.comment.dto.AddCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.ModifyCommentRequest


interface CommentService {
    fun getComment(todoId: Long): List<CommentResponse>
    fun addComment(todoId: Long, request: AddCommentRequest): CommentResponse
    fun modifyComment(
        todoId: Long,
        commentId: Long,
        request: ModifyCommentRequest
    ): CommentResponse

    fun deleteComment(todoId: Long, commentId: Long, request: DeleteCommentRequest)
}