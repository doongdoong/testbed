package com.koby.testbeddomain.domain.user.service

import com.koby.testbeddomain.domain.user.dto.UserDto
import com.koby.testbeddomain.domain.user.repository.mysql.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getUsersByName(name: String): List<UserDto> {
        return userRepository.findByName(name)
            .map { UserDto.from(it) }
    }

    fun getUserByEmail(email: String): UserDto? {
        return userRepository.findByEmail(email)?.let { UserDto.from(it) }
    }
}