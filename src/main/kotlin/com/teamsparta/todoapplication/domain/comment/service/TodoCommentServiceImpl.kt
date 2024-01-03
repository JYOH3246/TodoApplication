package com.teamsparta.todoapplication.domain.comment.service

import com.teamsparta.todoapplication.domain.comment.dto.AddTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.DeleteTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.ModifyTodoCommentRequest
import com.teamsparta.todoapplication.domain.comment.dto.TodoCommentResponse
import com.teamsparta.todoapplication.domain.comment.model.TodoComment
import com.teamsparta.todoapplication.domain.comment.model.toResponse
import com.teamsparta.todoapplication.domain.comment.repository.TodoCommentRepository
import com.teamsparta.todoapplication.domain.exception.IdAndPasswordNotCorrectException
import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.todo.repository.TodoRepository
import com.teamsparta.todoapplication.domain.todocard.repository.TodoCardRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoCommentServiceImpl(
    private val todoCardRepository: TodoCardRepository,
    private val todoRepository: TodoRepository,
    private val todoCommentRepository: TodoCommentRepository
) : TodoCommentService {

    @Transactional
    override fun addTodoComment(todoCardId: Long, todoId: Long, request: AddTodoCommentRequest): TodoCommentResponse {
        val todoCard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)
        val todoComment = TodoComment(
            comment = request.comment,
            name = request.name,
            date = request.date,
            password = request.password,
            todo = todo,
            todoCard = todoCard
        )
        todo.addTodoComment(todoComment)
        todoRepository.save(todo)
        todoCardRepository.save(todoCard)
        return todoComment.toResponse()

    }

    override fun getTodoComment(todoId: Long): List<TodoCommentResponse> {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Todo", todoId)

        return todo.todoComments.map { it.toResponse() }
    }


    @Transactional
    override fun modifyTodoComment(
        todoCardId: Long,
        todoId: Long,
        todoCommentId: Long,
        request: ModifyTodoCommentRequest
    ): TodoCommentResponse {
        val todoComment = todoCommentRepository.findByTodoCardIdAndTodoIdAndId(todoCardId, todoId, todoCommentId)
            ?: throw ModelNotFoundException("TodoComment", todoCommentId)
        // db에 request 받은 이름과 패스워드가 있는지 체크
        todoCommentRepository.findByNameAndPassword(request.name, request.password)
        if (todoComment.name == request.name && todoComment.password == request.password) {
            val (comment, name, date) = request
            todoComment.comment = comment
            todoComment.name = name
            todoComment.date = date
        }
        else {
            throw IdAndPasswordNotCorrectException(request.name,request.password)
        }
        return todoCommentRepository.save(todoComment).toResponse()

    }

    @Transactional
    override fun deleteTodoComment(
        todoCardId: Long,
        todoId: Long,
        todoCommentId: Long,
        request: DeleteTodoCommentRequest
    ) {
        TODO("입력받은 이름 비밀번호와 DB에 저장된 이름 비밀번호 비교하기")
        TODO("메시지와 상태코드 반환하기")
    }
}