package com.kotlin.board.service

import com.kotlin.board.repository.BoardRepository
import org.springframework.stereotype.Service

@Service
class BoardService(
        private val boardRepository: BoardRepository
) {

    fun getAllBoards() = boardRepository.findAll()

    fun getBoardByAuthor(author: String) = boardRepository.findByAuthor(author)
}