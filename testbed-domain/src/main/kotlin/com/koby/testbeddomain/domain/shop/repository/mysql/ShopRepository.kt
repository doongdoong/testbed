package com.koby.testbeddomain.domain.shop.repository.mysql

import com.koby.testbeddomain.domain.shop.entity.Shop
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ShopRepository(
    private val shopJpaRepository: ShopJpaRepository
): QuerydslRepositorySupport(Shop::class.java), ShopJpaRepository by shopJpaRepository {
    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }

}