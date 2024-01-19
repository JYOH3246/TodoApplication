package com.teamsparta.todoapplication.domain.todocard.service

import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.todocard.repository.TodoCardRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class TodoCardServiceTest: BehaviorSpec({
    extension(SpringExtension)
    afterContainer {
        clearAllMocks()
    }
    val todoCardRepository = mockk<TodoCardRepository>()
    val todoCardService = TodoCardServiceImpl(todoCardRepository)

    Given("TodoCard가 없을 때") {
        When("특정 TodoCard 확인을 요청하면") {
            Then("ModelNotFoundException이 발생한다.") {
                val todoCardId = 6L
                every { todoCardRepository.findByIdOrNull(any()) } returns null
                shouldThrow<ModelNotFoundException> {
                    todoCardService.getTodoCardById(todoCardId)
                }
            }
        }

    }
})

