package com.kotlin.board.exception

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserter
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler(errorAttributes: ErrorAttributes, applicationContext: ApplicationContext, serverCodecConfigurer: ServerCodecConfigurer) :
        AbstractErrorWebExceptionHandler(
        errorAttributes, WebProperties.Resources(), applicationContext) {

    init {
        super.setMessageReaders(serverCodecConfigurer.readers)
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse)
    }

    private fun renderErrorResponse(request: ServerRequest) : Mono<ServerResponse> {
        val errorMap = getErrorAttributes(request, ErrorAttributeOptions.defaults())

        return ServerResponse.status(errorMap.get("status").toString().toInt())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorMap))
    }
}