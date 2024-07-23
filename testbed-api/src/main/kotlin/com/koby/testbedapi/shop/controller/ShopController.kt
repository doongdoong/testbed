package com.koby.testbedapi.shop.controller

import com.koby.testbedapi.shop.service.ShopApiService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shop")
class ShopController(
    private val shopApiService: ShopApiService,
) {
    @GetMapping
    fun getAllShops(): ResponseEntity<*> {
        val shops = shopApiService.getAllShops()
        return ResponseEntity.ok(shops)
    }
}