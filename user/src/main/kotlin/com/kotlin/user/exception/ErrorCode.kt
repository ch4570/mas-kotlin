package com.kotlin.user.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
        val status: HttpStatus,
        val message: String
) {

    NOT_BLANK_REQUIRED_PROPERTY(HttpStatus.BAD_REQUEST, "필수 입력 값은 비어있을 수 없습니다."),
    NOT_PRESENT_MEMBER(HttpStatus.INTERNAL_SERVER_ERROR, "회원 조회 결과가 없습니다.")
}