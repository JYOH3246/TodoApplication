package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponseForAll
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
    override fun getAllTodo(todoCardId: Long): List<TodoResponseForAll> {
        val todoCard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todoCard.todos.map { it.toResponseForAll() }

    }

    override fun getTodoWithComments(todoCardId: Long, todoId: Long): List<TodoResponse> {
        return todoRepository.findByTodoWithComments(todoCardId, todoId).map { it.toResponse() }
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
            // todoCard의 인덱스
            todoCard = todoCard
        )
        // 글자수 제한 체크하고, 안걸리면 저장
        todoCard.addTodo(todo, request)
        todoCardRepository.save(todoCard)
        return todoRepository.save(todo).toResponseForAll()

    }

    @Transactional
    override fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequest): TodoResponse {
        // todoID가 없다면
        val todo = todoRepository.findBytodoCardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        // 글자수 제한 체크하고, 안걸리면 저장
        todo.modifyTodo(request)
        return todo.toResponse()

    }

    override fun getTodo(todoCardId: Long, todoId: Long): TodoResponse {
        val todoCard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todo.toResponse()
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