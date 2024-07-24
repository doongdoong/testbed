package com.koby.testbeddomain.domain.product.entity

import com.koby.testbeddomain.domain.shop.entity.Shop
import jakarta.persistence.*

import java.time.OffsetDateTime

@Entity
@Table(name = "products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val price: Int,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    var shop: Shop,
    @Column
    var dateDeleted: OffsetDateTime? = null,
) {
    fun addShop(shop: Shop) { // 연관관계 편의 메서드
        if (shop != null) { // 기존에 등록된 Shop이 있다면 제거해줘야 함. 그래야 순수 객체 관점에서도 기대하는 결과를 냄.
            this.shop.products.remove(this)
        }

        this.shop = shop
        shop.products.add(this)
    }
}