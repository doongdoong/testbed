package com.koby.testbeddomain.domain.shop.service

import com.koby.testbeddomain.domain.shop.dto.ShopDto
import com.koby.testbeddomain.domain.shop.repository.mysql.ShopRepository
import org.springframework.stereotype.Service

@Service
class ShopService(
    private val shopRepository: ShopRepository
) {
    fun getAllShops(): List<ShopDto> {
        return shopRepository.findAll().map { ShopDto.from(it) }
    }
}