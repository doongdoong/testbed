package com.koby.testbedapi.handler

import com.koby.testbeddomain.common.exception.CustomServerException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = LoggerFactory.getLogger(this::class.java)

    // =================================================================================================================
    // CustomServerException
    // =================================================================================================================
    @ExceptionHandler(CustomServerException::class)
    fun handleServerException(e: CustomServerException): ResponseEntity<ErrorResponse> {
        log.error("DefaultExceptionHandler.handleServerException: ", e)
        val errorResponse = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.errorCode.message)
        return ResponseEntity<ErrorResponse>(errorResponse, errorResponse.httpStatus)
    }

    // =================================================================================================================
    // Exception
    // =================================================================================================================
    @ExceptionHandler(Exception::class)
    fun handleDefaultException(e: Exception): ResponseEntity<ErrorResponse> {
        log.error("DefaultExceptionHandler.handleDefaultException: ", e)
        val errorResponse = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.cause?.toString() ?: e.toString())
        return ResponseEntity<ErrorResponse>(errorResponse, errorResponse.httpStatus)
    }
}

data class ErrorResponse(val httpStatus: HttpStatus, val message: String)