package com.koby.testbeddomain.common.configuration

import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.koby.testbeddomain"],
    transactionManagerRef = "testbedTransactionManager",
    entityManagerFactoryRef = "testbedEntityManagerFactory",
    includeFilters = [
        ComponentScan.Filter(
            type = FilterType.ASPECTJ,
            pattern = ["com.koby.testbeddomain..mysql.*Repository"]
        )
    ]
)
class JpaConfiguration(private val jpaProperties: JpaProperties) {
    @Primary
    @Bean
    fun testbedEntityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val builder = EntityManagerFactoryBuilder(createJpaVendorAdapter(), jpaProperties.properties, null)
        return builder.dataSource(dataSource)
            .persistenceUnit("testbedEntityManager")
            .packages("com.koby.testbeddomain")
            .build()
    }

    @Primary
    @Bean
    fun testbedTransactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    private fun createJpaVendorAdapter(): JpaVendorAdapter {
        val adapter = HibernateJpaVendorAdapter()
        adapter.setShowSql(jpaProperties.isShowSql)
        adapter.setDatabase(jpaProperties.database)
        adapter.setDatabasePlatform(jpaProperties.databasePlatform)
        adapter.setGenerateDdl(jpaProperties.isGenerateDdl)
        return adapter
    }
}