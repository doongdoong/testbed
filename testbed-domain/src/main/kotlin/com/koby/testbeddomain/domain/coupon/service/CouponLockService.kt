package com.koby.testbeddomain.domain.coupon.service

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class CouponLockService(
    private val redissonClient: RedissonClient,
    private val couponService: CouponService,
) {
    companion object {
        const val COUPON_DECREASE_LOCK_KEY = "coupon_decrease_lock_key"
    }

    fun lockCouponDecrease(id: Long) {
        val lock: RLock = redissonClient.getLock(COUPON_DECREASE_LOCK_KEY)

        try {
            val isLocked = lock.tryLock(5, 10, TimeUnit.SECONDS)

            if (!isLocked) return

            couponService.decrease(id)
        } catch (e: Exception) {
            e.printStackTrace();
        } finally {
            if(lock.isLocked) {
                lock.unlock();
            }
        }
    }
}