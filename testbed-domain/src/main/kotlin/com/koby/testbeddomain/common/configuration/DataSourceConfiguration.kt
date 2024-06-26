package com.koby.testbeddomain.common.configuration

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import javax.sql.DataSource


@Configuration
@EnableConfigurationProperties(DataSourceProperties::class)
class DataSourceConfiguration(
    private val dataSourceProperties: DataSourceProperties
) {

    @Bean(name = ["masterDataSource"])
    fun masterDataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dataSourceProperties.master.jdbcUrl
        config.username = dataSourceProperties.master.username
        config.password = dataSourceProperties.master.password
        config.driverClassName = dataSourceProperties.master.driverClassName
        config.maximumPoolSize = dataSourceProperties.master.hikari.maximumPoolSize
        return HikariDataSource(config)
    }

    @Bean(name = ["slaveDataSource"])
    fun slaveDataSource(): DataSource {
        val config = HikariConfig()
        config.jdbcUrl = dataSourceProperties.slave.jdbcUrl
        config.username = dataSourceProperties.slave.username
        config.password = dataSourceProperties.slave.password
        config.driverClassName = dataSourceProperties.slave.driverClassName
        config.maximumPoolSize = dataSourceProperties.slave.hikari.maximumPoolSize
        return HikariDataSource(config)
    }

    @Primary
    @Bean(name = ["routingDataSource"])
    fun routingDataSource(
        @Qualifier("masterDataSource") masterDataSource: DataSource,
        @Qualifier("slaveDataSource") slaveDataSource: DataSource
    ): DataSource {
        val routingDataSource = object : AbstractRoutingDataSource() {
            override fun determineCurrentLookupKey(): Any? {
                return if (TransactionalContextHolder.isReadOnly()) "slave" else "master"
            }
        }

        val dataSources: Map<Any, Any> = mapOf(
            "master" to masterDataSource,
            "slave" to slaveDataSource
        )

        routingDataSource.setTargetDataSources(dataSources)
        routingDataSource.setDefaultTargetDataSource(masterDataSource)
        return routingDataSource
    }
}

object TransactionalContextHolder {
    private val contextHolder = ThreadLocal<Boolean>()

    fun setReadOnly(readOnly: Boolean) {
        contextHolder.set(readOnly)
    }

    fun isReadOnly(): Boolean {
        return contextHolder.get() ?: false
    }
}