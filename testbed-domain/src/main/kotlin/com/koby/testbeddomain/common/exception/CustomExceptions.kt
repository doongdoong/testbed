package com.koby.testbeddomain.common.exception

data class CustomServerException(val errorCode: ErrorCode): RuntimeException()