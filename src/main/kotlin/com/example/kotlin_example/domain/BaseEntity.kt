package com.example.kotlin_example.domain

import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
) : BaseEntityId() {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    var createAt: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    var updateAt: LocalDateTime = LocalDateTime.now()
        protected set
}

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntityId : Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null
        protected set
}