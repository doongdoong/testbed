package com.koby.testbeddomain.domain.product.service

import com.koby.testbeddomain.domain.product.repository.mysql.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository
) {

}