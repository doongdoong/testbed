package com.koby.testbeddomain.domain.product.repository.mysql

import com.koby.testbeddomain.domain.product.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository: JpaRepository<Product, Long> {
}