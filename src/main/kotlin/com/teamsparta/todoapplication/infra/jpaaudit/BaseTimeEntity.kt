package com.teamsparta.todoapplication.infra.jpaaudit

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected var createdDate: LocalDateTime = LocalDateTime.MIN

    @LastModifiedDate
    @Column(nullable = false)
    protected var modifiedDate: LocalDateTime = LocalDateTime.MIN


}

