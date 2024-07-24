package com.koby.testbeddomain.domain.user.entity

import com.koby.testbeddomain.common.model.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val email: String,
    @Column
    var dateDeleted: OffsetDateTime? = null,
): BaseTimeEntity()