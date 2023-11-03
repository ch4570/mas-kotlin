package com.kotlin.user.config

import com.kotlin.user.handler.MemberHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router


@Configuration
class RouterConfig {

    @Bean
    fun routerFunction(memberHandler: MemberHandler) = router {
        accept(MediaType.APPLICATION_JSON).nest {
            POST("/api/members", memberHandler::saveMember)
            GET("/api/members", memberHandler::findAllMember)
            GET("/api/members/{memberId}", memberHandler::findMemberByMemberId)
            DELETE("/api/members/{memberId}", memberHandler::removeMemberByMemberId)
            PATCH("/api/members/{memberId}", memberHandler::modifyMember)
        }
    }

}