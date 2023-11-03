package com.kotlin.user.domain.validator

import com.kotlin.user.domain.dto.request.MemberRequestDto
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator

class MemberValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return MemberRequestDto::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "비밀번호는 비어있을 수 없습니다.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "닉네임은 비어있을 수 없습니다.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "이메일은 비어있을 수 없습니다.")

        // TODO: 이메일 정규식 검사 추가
    }
}