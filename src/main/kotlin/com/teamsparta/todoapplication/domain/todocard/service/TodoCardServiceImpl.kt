package com.teamsparta.todoapplication.domain.todocard.service

import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.todocard.dto.AddTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.ModifyTodoCardRequest
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse
import com.teamsparta.todoapplication.domain.todocard.model.TodoCard
import com.teamsparta.todoapplication.domain.todocard.model.toResponse
import com.teamsparta.todoapplication.domain.todocard.repository.TodoCardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoCardServiceImpl (

        private val todoCardRepository : TodoCardRepository

) : TodoCardService {
    override fun getAllTodoCard(): List<TodoCardResponse> {
        // todoRepository에 존재하는 todo를 전부 출력
        return todoCardRepository.findAll().map { it.toResponse() }
    }
    override fun getTodoCardById(todoCardId: Long): TodoCardResponse {
        // 예외처리 : todoId에 해당하는 Todo가 존재하지 않는다면 & todocard 정의
        val todocard = todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard",todoCardId)
        // DB : Todo목록을 가져와서 이를 TodoResponse로 변환해 반환하기
        return todocard.toResponse()
    }
    @Transactional

    override fun addTodoCard(request: AddTodoCardRequest): TodoCardResponse {
        // Todo를 추가해 DB에 저장 후
        return todoCardRepository.save(
                TodoCard(
                        name = request.name,
                        date = request.date
                        )
        //저장 결과를를 TodoResponse로 변환해 반환하기
        ).toResponse()
    }
    @Transactional

    override fun modifyTodoCard(todoCardId: Long, request: ModifyTodoCardRequest): TodoCardResponse {
        // 예외처리 : todoId에 해당하는 Todo가 존재하지 않는다면 & todocard 정의
        val todocard = todoCardRepository.findByIdOrNull(todoCardId) ?: throw ModelNotFoundException("TodoCard",todoCardId)
        // (name, date) 정의하기 : ModifyTodoCardRequest 내 변수들로 정의
        val (name,date) = request
        // 요청받은 값으로 현재값을 교체하기
        todocard.name = name
        todocard.date = date
        return todoCardRepository.save(todocard).toResponse()
    }
    @Transactional
    override fun deleteTodoCard(todoCardId: Long) {
        // TODO 예외처리 : 만약 todoId에 해당하는 Todo가 없다면
        // TODO DB : todoId에 해당하는 Todo를 가져와서 삭제
        TODO("Not yet implemented")
    }
}