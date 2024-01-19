package com.teamsparta.todoapplication.infra.jpaaudit

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseUserEntity : BaseTimeEntity() {
    @CreatedBy
    @Column(nullable = false, updatable = true)
    protected var createdMail : String? = null

    @LastModifiedBy
    @Column(nullable = false, updatable = true)
    protected var modifiedMail : String? = null
}