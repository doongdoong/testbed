package com.koby.testbedapi

import com.koby.testbeddomain.DomainConfigurationLoader
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import

@Import(DomainConfigurationLoader::class)
@ConfigurationPropertiesScan
@SpringBootApplication
class TestbedApiApplication

fun main(args: Array<String>) {
    runApplication<TestbedApiApplication>(*args)
}
