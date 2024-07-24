package com.koby.testbeddomain.common.exception

enum class ErrorCode(
    val message: String
) {
    /* User Exceptions */
    USER_NOT_FOUND("존재하지 않는 사용자입니다."),

    /* Shop Exceptions */
    SHOP_NOT_FOUND("존재하지 않는 샵입니다.")
}