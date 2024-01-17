package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.exception.ContentLetterException
import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.exception.TitleLetterLengthException
import com.teamsparta.todoapplication.domain.todo.dto.*
import com.teamsparta.todoapplication.domain.todo.model.Todo
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
        return todoCard.todos.map { it.toResponseForAll() }

    }

    override fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse {
        val todo = todoRepository.findBytodoCardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        return todo.let { TodoResponse.from(it) }
    }

    @Transactional

    override fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponseForAll {
        // 예외처리 : todoCardId에 해당하는 ID가 없다면

        val todoCard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = Todo(
            title = request.title,
            content = request.content,
            status = request.status,
            // todocard의 인덱스
            todoCard = todoCard
        )
        if (request.title.length in 1..200) {
            if (request.content.length in 1..1000) {
                todoCard.addTodo(todo)
                todoCardRepository.save(todoCard)
                return todoRepository.save(todo).toResponseForAll()
            } else {
                throw ContentLetterException(request.content)
            }
        } else {
            throw TitleLetterLengthException(request.title)
        }

    }

    @Transactional
    override fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequest): TodoResponseForAll {
        // todoID가 없다면
        val todo = todoRepository.findBytodoCardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        // 글자수 제한 체크하기
        todo.checkLetterSpace(request)
        return todoRepository.save(todo).toResponseForAll()

    }

    @Transactional
    override fun deleteTodo(todoCardId: Long, todoId: Long) {
        //예외처리 : todoCardId에 해당하는 ID가 없다면
        val todoCard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        // 예외처리 : todoId에 해당하는 ID가 없다면
        val todo = todoRepository.findBytodoCardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)

        // 삭제
        todoCard.removeTodo(todo)
        //영속성 전파
        todoCardRepository.save(todoCard)
    }
}