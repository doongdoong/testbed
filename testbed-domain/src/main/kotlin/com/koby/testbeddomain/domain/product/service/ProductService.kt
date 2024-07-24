package com.koby.testbeddomain.domain.product.service

import com.koby.testbeddomain.common.exception.CustomServerException
import com.koby.testbeddomain.common.exception.ErrorCode
import com.koby.testbeddomain.domain.product.entity.Product
import com.koby.testbeddomain.domain.product.repository.mysql.ProductRepository
import com.koby.testbeddomain.domain.shop.repository.mysql.ShopRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val shopRepository: ShopRepository,
) {
    fun saveTestProduct() {
        val shop = shopRepository.findShopById(1) ?: throw CustomServerException(ErrorCode.SHOP_NOT_FOUND)

        val testProduct = Product(
            name = "테스트 상품",
            price = 1000,
            shop = shop
        )

        productRepository.save(testProduct)
    }
}