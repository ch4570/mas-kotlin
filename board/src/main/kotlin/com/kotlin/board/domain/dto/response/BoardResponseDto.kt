package com.kotlin.board.domain.dto.response

import com.kotlin.board.domain.entity.Board
import java.time.LocalDateTime

class BoardResponseDto(
        val boardId: Long?,
        val title: String,
        val content: String,
        val author: String,
        val regDate: LocalDateTime?,
        val modDate: LocalDateTime?
) {

    companion object {
        fun mapToBoardDto(board: Board) =
                BoardResponseDto(
                        boardId = board.boardId,
                        title = board.title,
                        content = board.content,
                        author = board.author,
                        regDate = board.regDate,
                        modDate = board.regDate)
    }

}