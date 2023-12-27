package com.teamsparta.todoapplication.domain.todo.service

import com.teamsparta.todoapplication.domain.todo.dto.AddTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.GetTodoList
import com.teamsparta.todoapplication.domain.todo.dto.ModifyTodoRequest
import com.teamsparta.todoapplication.domain.todo.dto.TodoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
@Service
class TodoServiceImpl : TodoService {
    override fun getAllTodo(request: GetTodoList): List<TodoResponse> {
        // TODO: Email이 중복되는지 확인, 중복된다면 throw IllegalStateException
        TODO("Not yet implemented")
    }

    override fun getTodoById(todoId: Long): TodoResponse {
        // TODO 예외처리 : todoId에 해당하는 Todo가 존재하지 않는다면
        // TODO DB : Todo 목록을 가져와서 이를 TodoResponse로 변환해 반환하기
        TODO("Not yet implemented")
    }
    @Transactional
    override fun addTodo(request: AddTodoRequest): TodoResponse {
        // TODO DB : Todo를 추가해 DB에 저장 후, 저장 결과를를 TodoResponse로 변환해 반환하기
        TODO("Not yet implemented")
    }
    @Transactional
    override fun modifyTodo(todoId: Long, request: ModifyTodoRequest): TodoResponse {
        // TODO 예외처리 : 만약 todoId에 해당하는 Todo가 없다면
        // TODO DB : todoId에 해당하는 Todo를 가져와서, request로 업데이트 후 DB에 저장. 저장 결과를를 TodoResponse로 변환해 반환하기
        TODO("Not yet implemented")
    }
    @Transactional
    override fun deleteTodo(todoId: Long) {
        // TODO 예외처리 : 만약 todoId에 해당하는 Todo가 없다면
        // TODO DB : todoId에 해당하는 Todo를 가져와서 삭제
        TODO("Not yet implemented")
    }
}