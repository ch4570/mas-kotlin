package com.kotlin.user.exception

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class GlobalErrorAttributes: DefaultErrorAttributes() {

    override fun getErrorAttributes(request: ServerRequest?, options: ErrorAttributeOptions?): MutableMap<String, Any> {
        val errorMap = super.getErrorAttributes(request, options)

        val exception = getError(request)

        if (exception is CustomException) {
            return mutableMapOf(
                    "status" to exception.errorCode.status.value(),
                    "message" to exception.errorCode.message,
                    "timestamp" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss")),
                    "requestPath" to request?.requestPath().toString()
            )
        }

        return errorMap
    }
}