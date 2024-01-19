package com.teamsparta.todoapplication.domain.comment.service

import com.teamsparta.todoapplication.domain.comment.dto.AddCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.comment.dto.ModifyCommentRequest
import com.teamsparta.todoapplication.domain.comment.model.Comment
import com.teamsparta.todoapplication.domain.comment.model.toResponse
import com.teamsparta.todoapplication.domain.comment.repository.CommentRepository
import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val todoRepository: TodoRepository,
    private val commentRepository: CommentRepository
) : CommentService {

    @Transactional
    override fun addComment(todoId: Long, request: AddCommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val comment = Comment(
            content = request.content,
            todo = todo
        )
        todo.addComment(comment)
        todoRepository.save(todo)
        return comment.toResponse()

    }

    override fun getCommentById(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
        return comment.toResponse()
    }

    override fun getComment(todoId: Long): List<CommentResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        return todo.comments.map { it.toResponse() }
    }


    @Transactional
    override fun modifyComment(
        todoId: Long,
        commentId: Long,
        request: ModifyCommentRequest
    ): CommentResponse {
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        return commentRepository.save(comment).toResponse()

    }

    @Transactional
    override fun deleteComment(
        todoId: Long,
        commentId: Long,
    ) {
        val todo = todoRepository.findBytodoCardIdAndId(todoId, commentId)
            ?: throw ModelNotFoundException("Todo", todoId)
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)
        todo.removeComment(comment)
        todoRepository.save(todo)
    }
}
