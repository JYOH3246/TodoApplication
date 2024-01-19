package com.teamsparta.todoapplication.domain.user.service

import com.teamsparta.todoapplication.domain.user.dto.SignUpRequest
import com.teamsparta.todoapplication.domain.user.dto.UserResponse
import com.teamsparta.todoapplication.domain.user.model.User
import com.teamsparta.todoapplication.domain.user.model.toResponse
import com.teamsparta.todoapplication.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        /*
    1. 회원가입
    DB : request를 받은 이메일 패스워드 이름 별명을 저장하고 이를 response로 변환해 return
    예외사항 : 이메일 중복 / 별명 중복
     */
        // 예외처리 : 이메일 중복 체크 -> 중복이 아니면 닉네임 체크
        when {
            userRepository.existsByEmail(request.email) -> {
                throw IllegalStateException("Email is already in use")
            }

            else -> when {
                userRepository.existsByNickname(request.nickname) -> {
                    throw IllegalStateException("Nickname is already in use")
                }
            }
        }
        // 기능 : DB에 정보 저장 & 일부 데이터 Response로 반환
        return userRepository.save(
            User(
                // DB에 저장할 데이터들
                email = request.email,
                // TODO : 비밀번호 암호화
                password = request.password,
                name = request.name,
                nickname = request.nickname
            )
        ).toResponse() //response로 반환할 데이터들
    }
}