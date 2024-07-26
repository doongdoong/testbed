package com.koby.testbeddomain.domain.product.listener.event

import org.springframework.context.ApplicationEvent

class ProductLogEvent(
    val productId: Long,
    source: Any,
): ApplicationEvent(source)