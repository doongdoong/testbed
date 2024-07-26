package com.koby.testbeddomain.domain.product.service

import com.koby.testbeddomain.common.exception.CustomServerException
import com.koby.testbeddomain.common.exception.ErrorCode
import com.koby.testbeddomain.domain.pricing.PricingService
import com.koby.testbeddomain.domain.product.entity.Product
import com.koby.testbeddomain.domain.product.listener.event.ProductLogEvent
import com.koby.testbeddomain.domain.product.repository.mysql.ProductRepository
import com.koby.testbeddomain.domain.shop.service.ShopService
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.CompletableFuture

@Service
class ProductService(
    private val eventPublisher: ApplicationEventPublisher,
    private val productRepository: ProductRepository,
    private val shopService: ShopService,
    private val pricingService: PricingService,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)


    // 비동기 처리 테스트 함수
    @Transactional
    fun saveTestProduct() {
        logger.info("테스트 상품 생성 시작")
        val shopFuture = shopService.getShopById(1)
        val priceFuture = pricingService.getProductPrice()

        doSomethingForProductCreation()

        val savedProduct = CompletableFuture.allOf(shopFuture, priceFuture) // allOf()는 여러 개의 CompletableFuture를 동시에 대기하며, 모두 완료된 후에 단일 CompletableFuture를 반환
            .thenApply { // 비동기 작업의 결과를 처리하고, 그 결과를 기반으로 다른 값을 반환
                logger.info("in thenApply")

                val shop = shopFuture.join() ?: throw CustomServerException(ErrorCode.SHOP_NOT_FOUND)
                val price = priceFuture.join() // join()은 CompletableFuture가 완료될 때까지 대기하며 완료된 후에는 결과를 반환합니다.

                val testProduct = Product(
                    name = "테스트 상품",
                    price = price,
                    shop = shop
                )

                productRepository.save(testProduct)
            }
            .exceptionally { ex ->
                logger.error("비동기 작업 중 오류 발생", ex)
                throw ex
            }
            .join()

        eventPublisher.publishEvent(ProductLogEvent(savedProduct.id, this))

        logger.info("테스트 상품 생성 완료")
    }

    private fun doSomethingForProductCreation() {
        logger.info("상품 로직 작업 시작")
        Thread.sleep(2000)
        logger.info("상품 로직 작업 끝")
    }
}