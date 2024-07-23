package com.koby.testbeddomain.domain.shop.repository.mysql

import com.koby.testbeddomain.domain.shop.entity.Shop
import org.springframework.data.jpa.repository.JpaRepository

interface ShopJpaRepository: JpaRepository<Shop, Long> {
}