package com.example.kotlin_example.domain.post

import com.example.kotlin_example.domain.BaseEntity
import com.example.kotlin_example.domain.member.Member
import jakarta.persistence.*

@Entity
class Post(
    title: String,
    content: String,
    member: Member
) : BaseEntity() {
    @Column(nullable = false)
    var title: String = title
        private set
    var content: String = content
        private set
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        private set
}
