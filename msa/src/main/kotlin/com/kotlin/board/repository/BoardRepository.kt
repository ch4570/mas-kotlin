package com.kotlin.board.repository

import com.kotlin.board.domain.Board
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface BoardRepository: R2dbcRepository<Board, Long> {

    fun findByAuthor(author: String): Mono<Board>
    fun findByBoardId(boardId: Long?): Mono<Board>
}