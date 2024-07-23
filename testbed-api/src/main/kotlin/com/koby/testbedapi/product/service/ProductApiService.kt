package com.koby.testbedapi.product.service

import com.koby.testbeddomain.domain.product.service.ProductService
import org.springframework.stereotype.Service

@Service
class ProductApiService(
    private val productService: ProductService,
) {
}