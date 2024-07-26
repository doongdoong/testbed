package com.koby.testbeddomain.common.configuration

import org.slf4j.LoggerFactory
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@EnableAsync
@Configuration
class AsyncConfiguration: AsyncConfigurer {
    private val log = LoggerFactory.getLogger(this::class.simpleName)

    override fun getAsyncExecutor(): Executor {
        return asyncTaskExecutor()
    }

    override fun getAsyncUncaughtExceptionHandler(): AsyncUncaughtExceptionHandler {
        return AsyncUncaughtExceptionHandler { ex, method, params ->
            log.warn("unhandled exception: {} {} {}", method, params, ex.message)
        }
    }

    @Bean
    fun asyncTaskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 2
        executor.maxPoolSize = 5
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("DefaultAsyncEventExecutor-")
        executor.initialize()
        return executor
    }

    @Bean
    fun productLogEventTaskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 2
        executor.maxPoolSize = 5
        executor.setQueueCapacity(500)
        executor.setThreadNamePrefix("ProductLogEventExecutor-")
        executor.initialize()
        return executor
    }
}