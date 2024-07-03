package com.koby.testbedapi.user.service

import com.koby.testbedapi.user.dto.UserResponseDto
import com.koby.testbeddomain.domain.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserApiService(
    private val userService: UserService,
) {
    fun getUsersByName(name: String): List<UserResponseDto> {
        return userService.getUsersByName(name).map { UserResponseDto.from(it) }
    }

    fun getUserByEmail(email: String): UserResponseDto? {
        return userService.getUserByEmail(email)?.let { UserResponseDto.from(it) }
    }
}