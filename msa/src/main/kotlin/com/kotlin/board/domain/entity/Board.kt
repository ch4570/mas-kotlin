package com.kotlin.board.domain.entity

import com.kotlin.board.domain.dto.request.BoardRequestDto
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("BOARD")
class Board(
        @Id
        @Column("BOARD_ID")
        var boardId: Long? = null,

        @Column("TITLE")
        var title: String,

        @Column("CONTENT")
        var content: String,

        @Column("AUTHOR")
        var author: String,

        @Column("REG_DATE")
        @CreatedDate
        var regDate: LocalDateTime? = null,

        @Column("MOD_DATE")
        @LastModifiedDate
        var modDate: LocalDateTime? = null
){

    override fun toString(): String {
        return "Board(boardId=$boardId, title='$title', content='$content', author='$author', regDate=$regDate, modDate=$modDate)"
    }

    fun updateBoard(boardRequestDto: BoardRequestDto) : Board {
        title = boardRequestDto.title!!
        content = boardRequestDto.content!!
        author = boardRequestDto.author!!

        return this
    }
}