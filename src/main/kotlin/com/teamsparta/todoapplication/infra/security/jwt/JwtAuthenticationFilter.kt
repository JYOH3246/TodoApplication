package com.teamsparta.todoapplication.infra.security.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtPlugin: JwtPlugin
) : OncePerRequestFilter() {
    companion object {
        // Header로부터 JWT를 획득하기 위해, Bearer를 제거해 주는 정규식
        private val BEARER_PATTERN = Regex("^Bearer (.+?)$")
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val jwt = request.getBearerToken()

        if (jwt != null) { // 토큰이 null이 아니면
            jwtPlugin.validateToken(jwt)
                .onSuccess {
                    // 토큰으로부터 정보를 획득할 수 있다.
                    val userId = it.payload.subject.toLong()
                    val email = it.payload.get("email", String::class.java)
                    val role = it.payload.get("role", String::class.java)

                    val principal = UserPrincipal(
                        id = userId,
                        email = email,
                        roles = setOf(role)
                    )
                    // Authetication 구현체 SecurityContext에 저장
                    val authentication = JwtAuthenticationToken(
                        principal = principal,
                        details = WebAuthenticationDetailsSource().buildDetails(request)
                    )
                    SecurityContextHolder.getContext().authentication = authentication
                }
        }
        // filterChain 계속 진행 : 검증이 되지 않았다면 filterChain을 계속 진행하여 다음 필터들이 동작할 수 있도록 한다.
        filterChain.doFilter(request, response)
    }

    private fun HttpServletRequest.getBearerToken(): String? {
        val headerValue = this.getHeader(HttpHeaders.AUTHORIZATION) ?: return null
        return BEARER_PATTERN.find(headerValue)?.groupValues?.get(1)
    }
}

/*
JWT 인증 구현
1. 클라이언트가 요청시에 토큰을 보내는데, 그 토큰을 검증
Request의 Header에서 Bearer를 제외하고 JWT를 추출하고
해당 토큰을 검증하여 검증에 성공하면 Authentication 객체에 인증됐다는 것을 표시하고
SecurityContext에 저장

해야 하는 것
1. Filter 만들기
dofilter를 구현해야 하는데, 로직에 맞는 필터를 골라 써야 한다.
OncePerRequestFilter 사용하기 : 요청마다 토큰을 검증하는 필터
 */