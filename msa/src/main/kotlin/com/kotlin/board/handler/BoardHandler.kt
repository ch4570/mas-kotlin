package com.kotlin.board.handler

import com.kotlin.board.domain.dto.request.BoardRequestDto
import com.kotlin.board.domain.dto.response.BoardResponseDto
import com.kotlin.board.domain.entity.Board
import com.kotlin.board.domain.validator.BoardValidator
import com.kotlin.board.exception.CustomException
import com.kotlin.board.exception.ErrorCode.*
import com.kotlin.board.service.BoardService
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Component
class BoardHandler(
        private val boardService: BoardService
) {

    fun getAllBoards(serverRequest: ServerRequest) =
            boardService.getAllBoards()
                    .map { board -> BoardResponseDto.mapToBoardDto(board) }
                    .collectList()
                    .flatMap {
                        ServerResponse.ok().body(BodyInserters.fromValue(it))
                    }

    fun getBoardByBoardId(serverRequest: ServerRequest) =
            boardService.getBoardByBoardId(serverRequest.pathVariable("boardId").toLong())
                    .map { board -> BoardResponseDto.mapToBoardDto(board) }
                    .flatMap {
                        ServerResponse.ok().body(BodyInserters.fromValue(it))
                    }.switchIfEmpty(
                            Mono.error(CustomException(NOT_PRESENT_CONTENT)))


    fun saveBoard(serverRequest: ServerRequest) =
        serverRequest.bodyToMono(BoardRequestDto::class.java)
                .flatMap {
                    boardRequestDto ->
                    validate(boardRequestDto)
                    boardService.saveBoard(Board(content = boardRequestDto.content!!,
                            title = boardRequestDto.title!!,
                            author = boardRequestDto.author!!))

                    ServerResponse
                            .ok().build()
                }



    private fun validate(boardRequestDto: BoardRequestDto) {
        val validator = BoardValidator()
        val errors = BeanPropertyBindingResult(boardRequestDto, "boardRequestDto")
        validator.validate(boardRequestDto, errors)

        if (errors.hasErrors()) {
            throw CustomException(NOT_BLANK_REQUIRED_PROPERTY)
        }
    }
}

