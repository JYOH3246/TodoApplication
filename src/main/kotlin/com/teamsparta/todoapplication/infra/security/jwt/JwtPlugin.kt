package com.teamsparta.todoapplication.infra.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

/*
필요한 기능 정의
1. 토큰 검증하기 : 생성한 토큰이 맞는지 / 토큰의 만료기한이 되었는지
2. 토큰 생성하기
    2.1 알고리즘 : HS256, key가 32바이트 이상
    2.2 claim 지정
        iss : 발급자
        exp : 만료기간
        sub : 발급대상
    2.3 Custom Claim
        email
 */
@Component
class JwtPlugin(
    @Value("\${auth.jwt.issuer}") private val issuer: String,
    @Value("\${auth.jwt.secret}") private val secret: String,
    @Value("\${auth.jwt.accessTokenExpirationHour}") private val accessTokenExpirationHour: Long,
) {

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }
    }

    fun generateAccessToken(subject: String, email: String, role: String): String {
        return generateToken(subject, email,role, Duration.ofHours(accessTokenExpirationHour))
    }

    private fun generateToken(subject: String, email: String,role:String, expirationPeriod: Duration): String {
        val claims: Claims = Jwts.claims().add(mapOf("role" to role, "email" to email)).build()
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }


}
