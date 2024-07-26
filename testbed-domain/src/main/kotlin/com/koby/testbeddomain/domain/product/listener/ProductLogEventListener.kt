package com.koby.testbeddomain.domain.product.listener

import com.koby.testbeddomain.domain.product.listener.event.ProductLogEvent
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ProductLogEventListener {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    @Async(value = "productLogEventTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun onEventSaveProductLog(event: ProductLogEvent) {
        logger.info("created Product Id => ${event.productId}")
    }
}