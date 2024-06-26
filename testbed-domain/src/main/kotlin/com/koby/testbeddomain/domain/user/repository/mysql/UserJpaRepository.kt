package com.koby.testbeddomain.domain.user.repository.mysql

import com.koby.testbeddomain.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}