package com.kotlin.user.handler

import com.kotlin.user.domain.dto.request.MemberRequestDto
import com.kotlin.user.domain.dto.response.MemberResponseDto
import com.kotlin.user.domain.entity.Member
import com.kotlin.user.domain.validator.MemberValidator
import com.kotlin.user.exception.CustomException
import com.kotlin.user.exception.ErrorCode.*
import com.kotlin.user.service.MemberService
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class MemberHandler(
        private val memberService: MemberService
) {

    fun saveMember(serverRequest: ServerRequest) =
            serverRequest.bodyToMono(MemberRequestDto::class.java)
                    .flatMap {
                        requestDto ->
                        validate(requestDto)

                        val member = Member(
                                nickname = requestDto.nickname!!,
                                email = requestDto.email!!,
                                password = requestDto.password!!)

                        memberService.saveMember(member)
                        ServerResponse
                                .ok().build()
                    }


    fun findAllMember(serverRequest: ServerRequest) =
            memberService.findAllMember()
                    .map { MemberResponseDto.mapToMemberResponse(it) }
                    .collectList()
                    .flatMap {
                        ServerResponse
                                .ok().body(BodyInserters.fromValue(it))
                    }

    fun findMemberByMemberId(serverRequest: ServerRequest) =
            memberService.findMemberByMemberId(
                    serverRequest.pathVariable("memberId").toLong())
                    .flatMap {
                        ServerResponse
                                .ok().body(BodyInserters.fromValue(it))
                    }

    fun modifyMember(serverRequest: ServerRequest) =
            serverRequest.bodyToMono(MemberRequestDto::class.java)
                    .flatMap {
                        requestDto ->
                        validate(requestDto)

                        memberService.findMemberByMemberId(
                                serverRequest.pathVariable("memberId").toLong())
                                .flatMap {
                                    member ->
                                    memberService.saveMember(member.updateMember(requestDto))
                                    ServerResponse
                                            .ok().build()
                                }
                    }


    fun removeMemberByMemberId(serverRequest: ServerRequest) =
            memberService.removeMember(
                    serverRequest.pathVariable("memberId").toLong())
                    .then(Mono.defer {
                        ServerResponse
                                .ok().build()
                    })

    private fun validate(memberRequestDto: MemberRequestDto) {
        val validator = MemberValidator()
        val errors = BeanPropertyBindingResult(memberRequestDto, "memberRequestDto")
        validator.validate(memberRequestDto, errors)

        if (errors.hasErrors()) {
            throw CustomException(NOT_BLANK_REQUIRED_PROPERTY)
        }

    }
}