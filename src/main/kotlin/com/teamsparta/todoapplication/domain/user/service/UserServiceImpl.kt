package com.teamsparta.todoapplication.domain.user.service

import com.teamsparta.todoapplication.domain.exception.InvalidCredentialException
import com.teamsparta.todoapplication.domain.exception.InvalidInputException
import com.teamsparta.todoapplication.domain.exception.ModelNotFoundException
import com.teamsparta.todoapplication.domain.user.dto.LoginRequest
import com.teamsparta.todoapplication.domain.user.dto.LoginResponse
import com.teamsparta.todoapplication.domain.user.dto.SignUpRequest
import com.teamsparta.todoapplication.domain.user.dto.UserResponse
import com.teamsparta.todoapplication.domain.user.model.User
import com.teamsparta.todoapplication.domain.user.model.UserRole
import com.teamsparta.todoapplication.domain.user.model.toResponse
import com.teamsparta.todoapplication.domain.user.repository.UserRepository
import com.teamsparta.todoapplication.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {
    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        /*
    1. 회원가입
    DB : request를 받은 이메일 패스워드 이름 별명을 저장하고 이를 response로 변환해 return
    예외사항 : 이메일 중복 / 별명 중복
     */
        val user = User(
            // DB에 저장할 데이터들
            email = request.email,
            // 비밀번호 암호화
            password = passwordEncoder.encode(request.password),
            name = request.name,
            nickname = request.nickname,
            role = when (request.role) {
                "ADMIN" -> UserRole.ADMIN
                "MEMBER" -> UserRole.MEMBER
                else -> throw IllegalArgumentException("Invalid role")
            }
        )
        // 예외처리 : 이메일 중복 체크 -> 중복이 아니면 닉네임 체크
        val checkEmail = userRepository.existsByEmail(request.email)
        val checkNickname = userRepository.existsByNickname(request.nickname)
        user.checkEmailAndNickname(checkEmail, checkNickname)
        // 기능 : DB에 정보 저장 & 일부 데이터 Response로 반환
        return userRepository.save(user).toResponse() //response로 반환할 데이터들
    }

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email)?: throw ModelNotFoundException("User", null)
        if (user.role.name != request.role ||!passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialException()
        }
        return LoginResponse (
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name

            )
        )
    }
    // 내 정보 조회하기
    override fun searchMyInfo(id: Long): UserResponse {
        val user : User = userRepository.findByIdOrNull(id) ?:throw InvalidInputException(id)
        return user.toResponse()
    }
}