package com.koby.testbeddomain.domain.shop.dto

import com.koby.testbeddomain.domain.shop.entity.Shop
import java.time.OffsetDateTime

data class ShopDto(
    val id: Long,
    val name: String,
    val dateCreated: OffsetDateTime,
    val dateUpdated: OffsetDateTime,
    val dateDeleted: OffsetDateTime?
) {
    companion object {
        fun from(shop: Shop): ShopDto {
            return ShopDto(
                id = shop.id,
                name = shop.name,
                dateCreated = shop.dateCreated,
                dateUpdated = shop.dateUpdated,
                dateDeleted = shop.dateDeleted,
            )
        }
    }
}