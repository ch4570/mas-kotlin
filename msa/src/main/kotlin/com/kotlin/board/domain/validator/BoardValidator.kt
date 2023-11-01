package com.kotlin.board.domain.validator

import com.kotlin.board.domain.dto.request.BoardRequestDto
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator


class BoardValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return BoardRequestDto::class.java == clazz
    }

    override fun validate(target: Any, errors: Errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "제목은 비어있을 수 없습니다.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "내용은 비어있을 수 없습니다.")
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "작성자는 비어있을 수 없습니다.")
    }

}