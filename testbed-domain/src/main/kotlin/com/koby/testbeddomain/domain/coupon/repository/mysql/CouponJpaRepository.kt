package com.koby.testbeddomain.domain.coupon.repository.mysql

import com.koby.testbeddomain.domain.coupon.entity.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponJpaRepository: JpaRepository<Coupon, Long> {
    fun findFirstById(id: Long): Coupon?
}