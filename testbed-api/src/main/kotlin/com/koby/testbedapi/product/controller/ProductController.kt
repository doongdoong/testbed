package com.koby.testbedapi.product.controller

import com.koby.testbedapi.product.service.ProductApiService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/product"])
class ProductController(
    private val productApiService: ProductApiService
) {
    @PostMapping
    fun saveTestProduct() {
        productApiService.saveTestProduct()
    }
}