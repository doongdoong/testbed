package com.koby.testbeddomain.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("spring.datasource")
class DataSourceProperties {
    lateinit var master: DataSourceConfig
    lateinit var slave: DataSourceConfig

    class DataSourceConfig {
        lateinit var jdbcUrl: String
        lateinit var username: String
        lateinit var password: String
        lateinit var driverClassName: String
        var hikari: HikariConfigProperties = HikariConfigProperties()
    }

    class HikariConfigProperties {
        var maximumPoolSize: Int = 10
    }
}