package com.koby.testbeddomain.domain.user.repository.mysql

import com.koby.testbeddomain.domain.user.entity.QUser.user
import com.koby.testbeddomain.domain.user.entity.User
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository

@Repository
class UserRepository(
    private val userJpaRepository: UserJpaRepository,
): QuerydslRepositorySupport(User::class.java), UserJpaRepository by userJpaRepository {
    @PersistenceContext
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }

    fun findByName(name: String): List<User> {
        return from(user)
            .select(user)
            .where(user.name.eq(name))
            .fetch()
    }
}