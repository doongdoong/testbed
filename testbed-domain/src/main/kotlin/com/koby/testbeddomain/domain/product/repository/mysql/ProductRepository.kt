package com.koby.testbeddomain.domain.product.repository.mysql

import com.koby.testbeddomain.domain.product.entity.Product
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class ProductRepository(
    private val productJpaRepository: ProductJpaRepository
): QuerydslRepositorySupport(Product::class.java), ProductJpaRepository by productJpaRepository {
    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }

}