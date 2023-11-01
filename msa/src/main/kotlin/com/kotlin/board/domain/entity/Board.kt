package com.kotlin.board.domain.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("BOARD")
class Board(
        @Column("BOARD_ID")
        val boardId: Long? = null,

        @Column("TITLE")
        val title: String,

        @Column("CONTENT")
        val content: String,

        @Column("AUTHOR")
        val author: String,

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
}