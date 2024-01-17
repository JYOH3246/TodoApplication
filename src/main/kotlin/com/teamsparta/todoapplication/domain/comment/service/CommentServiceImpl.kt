package com.teamsparta.todoapplication.domain.comment.service

import com.teamsparta.todoapplication.domain.comment.dto.AddCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.CommentResponse
import com.teamsparta.todoapplication.domain.comment.dto.DeleteCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.ModifyCommentRequest
import com.teamsparta.todoapplication.domain.comment.model.Comment
import com.teamsparta.todoapplication.domain.comment.model.toResponse
import com.teamsparta.todoapplication.domain.comment.repository.CommentRepository
import com.teamsparta.todoapplication.domain.exception.IdAndPasswordNotCorrectException
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
            name = request.name,
            password = request.password,
            todo = todo
        )
        todo.addComment(comment)
        todoRepository.save(todo)
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
        // db에 request 받은 이름과 패스워드가 있는지 체크
        commentRepository.findByNameAndPassword(request.name, request.password)
        if (comment.name == request.name && comment.password == request.password) {
            val (content, name) = request
            comment.content = content
            comment.name = name
        } else {
            throw IdAndPasswordNotCorrectException(request.name, request.password)
        }
        return commentRepository.save(comment).toResponse()

    }

    @Transactional
    override fun deleteComment(
        todoId: Long,
        commentId: Long,
        request: DeleteCommentRequest
    ) {
        val todo = todoRepository.findBytodoCardIdAndId(todoId,commentId)
            ?: throw ModelNotFoundException("Todo", todoId)
        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw ModelNotFoundException("Comment", commentId)

        commentRepository.findByNameAndPassword(request.name, request.password)
        if (comment.name == request.name && comment.password == request.password) {
            todo.removeComment(comment)
            todoRepository.save(todo)
        } else {
            throw IdAndPasswordNotCorrectException(request.name, request.password)
        }
    }
}
