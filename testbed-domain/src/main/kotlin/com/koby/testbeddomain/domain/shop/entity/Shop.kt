package com.koby.testbeddomain.domain.shop.entity

import com.koby.testbeddomain.common.model.BaseTimeEntity
import com.koby.testbeddomain.domain.product.entity.Product
import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.OffsetDateTime

@Entity
@Table(name = "shops")
@SQLRestriction("date_deleted IS NULL")
class Shop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false)
    val name: String,
    @OneToMany(mappedBy="shop", fetch = FetchType.LAZY)
    var products: MutableSet<Product> = mutableSetOf(),
    @Column
    var dateDeleted: OffsetDateTime? = null,
): BaseTimeEntity()