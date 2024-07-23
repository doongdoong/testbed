package com.koby.testbedapi.shop.dto

import com.koby.testbeddomain.domain.shop.dto.ShopDto
import java.time.OffsetDateTime

data class ShopResponseDto(
    val id: Long,
    val name: String,
    val dateCreated: OffsetDateTime,
    val dateUpdated: OffsetDateTime,
    val dateDeleted: OffsetDateTime?
) {
    companion object {
        fun from(dto: ShopDto): ShopResponseDto {
            return ShopResponseDto(
                id = dto.id,
                name = dto.name,
                dateCreated = dto.dateCreated,
                dateUpdated = dto.dateUpdated,
                dateDeleted = dto.dateDeleted,
            )
        }
    }
}
