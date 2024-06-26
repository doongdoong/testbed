package com.koby.testbedapi.user.dto

import com.koby.testbeddomain.domain.user.dto.UserDto
import java.time.OffsetDateTime

data class UserResponseDto(
    val id: Long,
    val name: String,
    val email: String,
    val dateCreated: OffsetDateTime,
    val dateUpdated: OffsetDateTime,
    val dateDeleted: OffsetDateTime?
) {
    companion object {
        fun from(userDto: UserDto): UserResponseDto {
            return UserResponseDto(
                id = userDto.id,
                name = userDto.name,
                email = userDto.email,
                dateCreated = userDto.dateCreated,
                dateUpdated = userDto.dateUpdated,
                dateDeleted = userDto.dateDeleted,
            )
        }
    }
}