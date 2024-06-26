package com.koby.testbeddomain.domain.user.dto

import com.koby.testbeddomain.domain.user.entity.User
import java.time.OffsetDateTime

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val dateCreated: OffsetDateTime,
    val dateUpdated: OffsetDateTime,
    val dateDeleted: OffsetDateTime?
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id,
                name = user.name,
                email = user.email,
                dateCreated = user.dateCreated,
                dateUpdated = user.dateUpdated,
                dateDeleted = user.dateDeleted,
            )
        }
    }
}