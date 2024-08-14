package com.koby.testbeddomain.domain.coupon.entity

import com.koby.testbeddomain.common.exception.CustomServerException
import com.koby.testbeddomain.common.exception.ErrorCode
import com.koby.testbeddomain.common.model.BaseTimeEntity
import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "coupons")
class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    var quantity: Int = 0,
    @Column
    var dateDeleted: OffsetDateTime? = null,
): BaseTimeEntity() {

    fun decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--
            println("${Thread.currentThread().name} 스레드의 쿠폰 재고 감소: 현재 ${this.quantity}개")
        }
        else {
            throw CustomServerException(ErrorCode.COUPON_QUANTITY_ZERO)
        }
    }
}