package com.koby.testbedapi.shop.service

import com.koby.testbedapi.shop.dto.ShopResponseDto
import com.koby.testbeddomain.domain.shop.service.ShopService
import org.springframework.stereotype.Service

@Service
class ShopApiService(
    private val shopService: ShopService,
) {
    fun getAllShops(): List<ShopResponseDto> {
        return shopService.getAllShopsWithFetchJoin().map { ShopResponseDto.from(it) }
    }
}