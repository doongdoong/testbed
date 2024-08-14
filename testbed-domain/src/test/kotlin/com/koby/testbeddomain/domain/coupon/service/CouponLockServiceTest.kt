package com.koby.testbeddomain.domain.coupon.service

import com.koby.testbeddomain.DomainConfigurationLoader
import com.koby.testbeddomain.domain.coupon.entity.Coupon
import com.koby.testbeddomain.domain.coupon.repository.mysql.CouponJpaRepository
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.Executors
import kotlin.test.assertEquals

@SpringBootTest(classes = [DomainConfigurationLoader::class])
@ActiveProfiles("test")
class CouponLockServiceTest {
    @Autowired
    private lateinit var couponLockService: CouponLockService

    @Autowired
    private lateinit var couponJpaRepository: CouponJpaRepository

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Test
    fun couponQuantityConcurrencyTest() {
        val initialCouponQuantity = 1000
        val newCoupon = Coupon(
            name = "Test Coupon",
            quantity = initialCouponQuantity
        )
        couponJpaRepository.save(newCoupon)
        val couponId = newCoupon.id

        val numberOfThreads = 100
        val service = Executors.newFixedThreadPool(100)
        for (i in 0 until numberOfThreads) {
            service.execute {
                couponLockService.lockCouponDecrease(couponId)
            }
        }

        Thread.sleep(5000)

        val afterCouponQuantity = couponJpaRepository.findFirstById(couponId)!!.quantity
        logger.info("최종 쿠폰 갯수: $afterCouponQuantity")

        assertEquals((initialCouponQuantity - numberOfThreads), afterCouponQuantity)
    }
}