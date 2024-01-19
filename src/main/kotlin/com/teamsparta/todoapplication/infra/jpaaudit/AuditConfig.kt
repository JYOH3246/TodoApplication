package com.teamsparta.todoapplication.infra.jpaaudit

import com.teamsparta.todoapplication.infra.security.jwt.UserPrincipal
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

@Configuration
class AuditConfig {
    @Bean
    fun auditorAware() = AuditorAware {
        val userPrincipal: UserPrincipal
        Optional.ofNullable(SecurityContextHolder.getContext())
            .map { it.authentication }
            .filter { it.isAuthenticated && !it.name.equals("anonymousUser") }
            .map { it.principal as UserPrincipal }
            .map { it.email }
    }
}