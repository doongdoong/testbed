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
    val shop: Shop,
    @Column
    var dateDeleted: OffsetDateTime? = null,
) {
}