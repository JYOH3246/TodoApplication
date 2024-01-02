package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequset
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import com.teamsparta.todoapplication.domain.todo.model.Todo
import com.teamsparta.todoapplication.domain.todo.model.toResponse
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
    override fun getAllTodo(todoCardId: Long): List<TodoResponse> {
        val todocard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        return todocard.todos.map { it.toResponse() }

    }

    override fun getTodoById(todoCardId: Long, todoId: Long): TodoResponse {
        val todo = todoRepository.findByTodocardIdAndId(todoCardId, todoId)
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
        // Card에 todo를 추가하기 : TodoCard.kt에 함수를 구현하자
        todocard.addTodo(todo)
        // 하위항목 추가 시 영속성 전파하기 todo->todoCard->DB
        todoCardRepository.save(todocard)
        //요청받은 todo값을 response로 변환
        return todo.toResponse()
    }

    @Transactional
    override fun modifyTodo(todoCardId: Long, todoId: Long, request: ModifyTodoRequset): TodoResponse {
        // todoID가 없다면
        // TODO("status 추가")
        val todo = todoRepository.findByTodocardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        // 수정할 변수들을 정의하기
        val (title, content, date, status: Boolean) = request
        // 요청받은 값을 변수에 대입
        todo.title = title
        todo.content = content
        todo.date = date
        todo.status = status
        // DB에 저장하고 response로 변환
        return todoRepository.save(todo).toResponse()

    }

    @Transactional
    override fun deleteTodo(todoCardId: Long, todoId: Long) {
        //예외처리 : todoCardId에 해당하는 ID가 없다면
        val todocard =
            todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard", todoCardId)
        // 예외처리 : todoId에 해당하는 ID가 없다면
        val todo = todoRepository.findByTodocardIdAndId(todoCardId, todoId)
            ?: throw ModelNotFoundException("Todo", todoId)
        // 삭제
        todocard.removeTodo(todo)
        //영속성 전파
        todoCardRepository.save(todocard)
    }
}