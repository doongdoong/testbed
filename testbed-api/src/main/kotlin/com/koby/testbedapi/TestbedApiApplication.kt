package com.koby.testbedapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TestbedApiApplication

fun main(args: Array<String>) {
    runApplication<TestbedApiApplication>(*args)
}
