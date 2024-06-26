package com.koby.testbedapi.user.controller

import com.koby.testbedapi.user.dto.UserResponseDto
import com.koby.testbeddomain.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/name/{name}")
    fun getUsersByName(@PathVariable name: String): ResponseEntity<*> {
        val users = userService.getUsersByName(name).map { UserResponseDto.from(it) }
        return ResponseEntity.ok(users)
    }

    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<UserResponseDto> {
        val user = userService.getUserByEmail(email)?. let { UserResponseDto.from(it) }
        return ResponseEntity.ok(user)
    }
}