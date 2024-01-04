package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.exception.ContentLetterException
import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.exception.TitleLetterLengthException
import com.teamsparta.todoapplication.domain.todo.dto.*
import com.teamsparta.todoapplication.domain.todo.model.Todo
import com.teamsparta.todoapplication.domain.todo.model.toResponse
import com.teamsparta.todoapplication.domain.todo.model.toResponseForAll
import com.teamsparta.todoapplication.domain.todo.repository.TodoRepository
import com.teamsparta.todoapplication.domain.todocard.repository.TodoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoCardRepository: TodoCardRepository,
    private val todoRepository: TodoRepository
) : TodoService {
    override fun getAllTodo(todoCardId: Long, request: GetTodoRequest): List<TodoResponseForAll> {
        val todoCard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        when {
            (request == GetTodoRequest.ASC) -> todoCard.todos = todoRepository.findAllByOrderByDateAsc()
            (request == GetTodoRequest.DESC) -> todoCard.todos = todoRepository.findAllByOrderByDateDesc()
        }
        return todoCard.todos.map { it.toResponseForAll() }

    }

    override fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse {
        val todo = todoRepository.findBytodocardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        return todo.toResponse()
    }

    @Transactional

    override fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponse {
        // 예외처리 : todoCardId에 해당하는 ID가 없다면

        val todocard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = Todo(
            title = request.title,
            content = request.content,
            date = request.date,
            status = request.status,
            // todocard의 인덱스
            todocard = todocard
        )
        if (request.title.length in 1..200) {
            if (request.content.length in 1..1000) {
                todocard.addTodo(todo)
                todoCardRepository.save(todocard)
                return todoRepository.save(todo).toResponse()
            } else {
                throw ContentLetterException(request.content)
            }
        } else {
            throw TitleLetterLengthException(request.title)
        }

    }

    @Transactional
    override fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequest): TodoResponse {
        // todoID가 없다면
        val todo = todoRepository.findBytodocardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        // 수정할 변수들을 정의하기
        val (title, content, date, status: Boolean) = request
        // 요청받은 값을 변수에 대입
        todo.title = title
        todo.content = content
        todo.date = date
        todo.status = status
        if (request.title.length in 1..200) {
            if (request.content.length in 1..1000) {
                return todoRepository.save(todo).toResponse()
            } else {
                throw ContentLetterException(request.content)
            }
        } else {
            throw TitleLetterLengthException(request.title)
        }
    }

    @Transactional
    override fun deleteTodo(todoCardId: Long, todoId: Long) {
        //예외처리 : todoCardId에 해당하는 ID가 없다면
        val todocard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        // 예외처리 : todoId에 해당하는 ID가 없다면
        val todo = todoRepository.findBytodocardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        // 삭제
        todocard.removeTodo(todo)
        //영속성 전파
        todoCardRepository.save(todocard)
    }
}