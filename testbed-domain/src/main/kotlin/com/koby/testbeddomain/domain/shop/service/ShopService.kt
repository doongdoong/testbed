package com.koby.testbeddomain.domain.shop.service

import com.koby.testbeddomain.domain.shop.dto.ShopDto
import com.koby.testbeddomain.domain.shop.entity.Shop
import com.koby.testbeddomain.domain.shop.repository.mysql.ShopRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

@Service
class ShopService(
    private val shopRepository: ShopRepository,
    private val shopTaskExecutor: Executor,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getAllShops(): List<ShopDto> {
        return shopRepository.findAll().map { ShopDto.from(it) }
    }

    fun getAllShopsWithFetchJoin(): List<ShopDto> {
        return shopRepository.findAllWithFetchJoin().map { ShopDto.from(it) }
    }

    fun getShopById(id: Long): CompletableFuture<Shop> {
        return CompletableFuture.supplyAsync ({
            logger.info("샵 정보 조회")
            Thread.sleep(3000)
            shopRepository.findShopById(id)
        }, shopTaskExecutor)
    }
}