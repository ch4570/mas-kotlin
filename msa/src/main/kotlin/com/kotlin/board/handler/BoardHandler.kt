package com.kotlin.board.handler

import com.kotlin.board.service.BoardService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class BoardHandler(
        private val boardService: BoardService
) {

    fun getAllBoards(serverRequest: ServerRequest) : Mono<ServerResponse> {
        return boardService.getAllBoards()
                .collectList()
                .flatMap {
                    ServerResponse.ok().body(BodyInserters.fromValue(it))
                }
    }

    fun getBoardByAuthor(serverRequest: ServerRequest): Mono<ServerResponse> {
        val author = serverRequest.pathVariable("author")
        return boardService.getBoardByAuthor(author)
                .flatMap {
                    ServerResponse.ok().body(BodyInserters.fromValue(it))
                }.switchIfEmpty(
                        Mono.defer {
                            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body(Mono.just("데이터가 없습니다."), String::class.java)
                        }
                )
    }

}