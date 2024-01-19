package com.teamsparta.todoapplication.domain.todocard.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.teamsparta.todoapplication.domain.todocard.dto.TodoCardResponse
import com.teamsparta.todoapplication.domain.todocard.service.TodoCardService
import com.teamsparta.todoapplication.infra.security.jwt.JwtPlugin
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class TodoCardControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin,
) : DescribeSpec({

    afterContainer {
        clearAllMocks()
    }

    val todoCardService = mockk<TodoCardService>()

    describe("GET /todoCards/{id} 는") { // 테스트 대상은 GET /todoCard/{id} API
        context("존재하는 ID에 대한 요청을 보낼 때") {
            it("200 status code를 응답해야 한다.") {
                // test 대상의 상황 : Id에 대한 요청을 보냄. todoCardId = 1L
                val todoCardId = 1L
                every { todoCardService.getTodoCardById(any()) } returns TodoCardResponse(
                    id = todoCardId,
                    name = "string"
                )

                // 현재 Security에 의해 인가 조건이 걸려있으므로, AccessToken 추가
                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "test@test.com",
                    role = "MEMBER"
                )
                val result = mockMvc.perform(
                    get("/todoCards/$todoCardId")
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()
                // it : 테스트 대상은 ~해야 한다
                result.response.status shouldBe 200
                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    TodoCardResponse::class.java
                )
                // it : 테스트 대상은 ~해야 한다
                responseDto.id shouldBe todoCardId
            }
        }
        context("존재하지 않는 ID에 대한 요청을 보내면") {
            it("404 status code를 응답해야 한다.") {
                val todoCardId = 2L
                every { todoCardService.getTodoCardById(any()) } returns TodoCardResponse(
                    id = todoCardId,
                    name = "strin1g"
                )

                // 현재 Security에 의해 인가 조건이 걸려있으므로, AccessToken 추가
                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "1",
                    email = "test@test.com",
                    role = "MEMBER"
                )
                val result = mockMvc.perform(
                    get("/todoCards/$todoCardId")
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()
                // it : 테스트 대상은 ~해야 한다
                result.response.status shouldBe 404
            }
        }
    }
})
