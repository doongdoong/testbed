package com.koby.testbeddomain.domain.coupon.service

import com.koby.testbeddomain.domain.coupon.repository.mysql.CouponJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
    private val couponJpaRepository: CouponJpaRepository,
) {
    @Transactional
    fun decrease(id: Long) {
        val coupon = couponJpaRepository.findFirstById(id) ?: return
        coupon.decreaseQuantity()
    }
}