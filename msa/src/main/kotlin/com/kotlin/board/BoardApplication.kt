package com.kotlin.board

import com.kotlin.board.domain.entity.Board
import com.kotlin.board.repository.BoardRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing

@SpringBootApplication
@EnableR2dbcAuditing
class BoardApplication(
		private val boardRepository: BoardRepository
) : CommandLineRunner{

	override fun run(vararg args: String?) {
		val board1 = Board(title = "웹 플럭스1",
				content = "웹 플럭스1 최고",
				author = "콜인이1")

		val board2 = Board(title = "웹 플럭스2",
				content = "웹 플럭스2 최고",
				author = "콜인이2")

		val boardList = listOf(board1, board2)
		boardRepository.saveAll(boardList).subscribe()
	}

}

fun main(args: Array<String>) {
	runApplication<BoardApplication>(*args)
}
