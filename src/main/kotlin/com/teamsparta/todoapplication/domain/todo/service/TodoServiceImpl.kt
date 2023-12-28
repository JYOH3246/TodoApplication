package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequset
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todo.model.toResponse
import com.teamsparta.todoapplication.domain.todo.repository.TodoRepository
import com.teamsparta.todoapplication.domain.todocard.repository.TodoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl (
        private val todoCardRepository: TodoCardRepository,
        private val todoRepository: TodoRepository
) : TodoService {
    override fun getAllTodo(todoCardId: Long): List<TodoResponse> {
        val todocard = todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard",todoCardId)
        return todocard.todos.map { it.toResponse() }

    }

    override fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse {
        val todo = todoRepository.findByTodocardIdAndId(todoCardId,todoId)
                ?: throw ModelNotFoundException("Todo",todoId)
        return todo.toResponse()
    }
    @Transactional
    override fun addTodo(todoCardId: Long, request: AddTodoRequest): TodoResponse {
        TODO("예외처리 : todoCardId에 해당하는 ID가 없다면")
        TODO("변수 todo를 정의하기")
        TODO("Card에 todo를 추가하기")
        TODO("하위항목 추가 시 영속성 전파하기 : todo -> todoCard -> DB ")
    }
    @Transactional
    override fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequset): TodoResponse {
        TODO("예외처리 : todoCardId에 해당하는 ID가 없다면")
        TODO("예외처리 : todoId에 해당하는 ID가 없다면")
        TODO("수정할 변수들을 정의하기")
        TODO("요청받은 값을 변수에 대입")
        TODO("DB에 저장하고 response로 변환")

    }
    @Transactional
    override fun deleteTodo(todoCardId: Long, todoId: Long) {
        TODO("예외처리 : todoCardId에 해당하는 ID가 없다면")
        TODO("예외처리 : todoId에 해당하는 ID가 없다면")
        TODO("DB에 저장된 값을 삭제")
        TODO("영속성 전파")
    }
}