package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoCardResponse
import com.teamsparta.todoapplication.domain.todo.model.toResponse
import com.teamsparta.todoapplication.domain.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceCardImpl (
        private val todoRepository : TodoRepository

) : TodoCardService {
    override fun getAllTodoCard(): List<TodoCardResponse> {
        // todoRepository에 존재하는 todo를 전부 출력
        return todoRepository.findAll().map { it.toResponse() }

    }

    override fun getTodoCardById(todoId: Long): TodoCardResponse {
        // TODO 예외처리 : todoId에 해당하는 Todo가 존재하지 않는다면
        // TODO DB : Todo 목록을 가져와서 이를 TodoResponse로 변환해 반환하기
        TODO("Not yet implemented")
    }
    @Transactional
    override fun addTodoCard(request: AddTodoRequest): TodoCardResponse {
        // TODO DB : Todo를 추가해 DB에 저장 후, 저장 결과를를 TodoResponse로 변환해 반환하기
        TODO("Not yet implemented")
    }
    @Transactional
    override fun modifyTodoCard(todoId: Long, request: ModifyTodoRequest): TodoCardResponse {
        // TODO 예외처리 : 만약 todoId에 해당하는 Todo가 없다면
        // TODO DB : todoId에 해당하는 Todo를 가져와서, request로 업데이트 후 DB에 저장. 저장 결과를를 TodoResponse로 변환해 반환하기
        TODO("Not yet implemented")
    }
    @Transactional
    override fun deleteTodoCard(todoId: Long) {
        // TODO 예외처리 : 만약 todoId에 해당하는 Todo가 없다면
        // TODO DB : todoId에 해당하는 Todo를 가져와서 삭제
        TODO("Not yet implemented")
    }
}