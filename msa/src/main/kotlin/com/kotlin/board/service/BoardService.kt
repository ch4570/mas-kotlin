package com.kotlin.board.service

import com.kotlin.board.domain.dto.request.BoardRequestDto
import com.kotlin.board.domain.entity.Board
import com.kotlin.board.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
        private val boardRepository: BoardRepository
) {

    fun getAllBoards() = boardRepository.findAll()

    fun getBoardByBoardId(boardId: Long) = boardRepository.findById(boardId)

    fun saveBoard(board: Board) = boardRepository.save(board).subscribe()


    fun removeBoard(boardId: Long) = boardRepository.deleteById(boardId)
}