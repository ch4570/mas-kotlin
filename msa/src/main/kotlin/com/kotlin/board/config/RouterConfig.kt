package com.kotlin.board.config

import com.kotlin.board.handler.BoardHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class RouterConfig(
) {

    @Bean
    fun routerFunction(boardHandler: BoardHandler) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            GET("/api/boards", boardHandler::getAllBoards)
            GET("/api/boards/{boardId}", boardHandler::getBoardByBoardId)
            POST("/api/boards", boardHandler::saveBoard)
            PATCH("/api/boards/{boardId}", boardHandler::modifyBoard)
        }
    }

}