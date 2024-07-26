package com.koby.testbeddomain.domain.pricing

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

@Service
class PricingService(
    private val pricingTaskExecutor: Executor,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getProductPrice(): CompletableFuture<Int> { // 비동기 테스트를 위해 만든 사실 별 의미 없는 함수
        return CompletableFuture.supplyAsync ({
            logger.info("가격 정보 조회")
            Thread.sleep(1000)
            1000
        }, pricingTaskExecutor)
    }
}