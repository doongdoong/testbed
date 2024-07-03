package com.koby.testbedapi.user.controller

import com.koby.testbedapi.user.dto.UserResponseDto
import com.koby.testbedapi.user.service.UserApiService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userApiService: UserApiService,
) {
    @GetMapping("/name/{name}")
    fun getUsersByName(@PathVariable name: String): ResponseEntity<*> {
        val users = userApiService.getUsersByName(name)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<UserResponseDto> {
        val user = userApiService.getUserByEmail(email)
        return ResponseEntity.ok(user)
    }
}