package com.teamsparta.todoapplication.domain.todocard.model

import com.teamsparta.todoapplication.domain.todo.model.Todo
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class TodoCardTest : BehaviorSpec({
    extension(SpringExtension)
    afterContainer {
        clearAllMocks()
    }

    val todo = mockk<Todo>()
    Given("TodoCard가 있을 때") {
        When("특정 Todo를 삭제하면") {
            Then("잘 삭제된다.") {

                val todos: MutableList<Todo> = mutableListOf(todo)
                every { todos.remove(any()) } returns true
                val result = todos.remove(todos[0])
                result shouldBe true


            }
        }
    }

})

