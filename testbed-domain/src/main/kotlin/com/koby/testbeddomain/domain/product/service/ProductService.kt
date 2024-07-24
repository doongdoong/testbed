package com.koby.testbeddomain.domain.product.service

import com.koby.testbeddomain.common.exception.CustomServerException
import com.koby.testbeddomain.common.exception.ErrorCode
import com.koby.testbeddomain.domain.product.entity.Product
import com.koby.testbeddomain.domain.product.repository.mysql.ProductRepository
import com.koby.testbeddomain.domain.shop.service.ShopService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val shopService: ShopService,
) {
    @Transactional
    fun saveTestProduct() {
        val shop = shopService.getShopById(1) ?: throw CustomServerException(ErrorCode.SHOP_NOT_FOUND)

        val testProduct = Product(
            name = "테스트 상품",
            price = 1000,
            shop = shop
        )

        productRepository.save(testProduct)
    }
}