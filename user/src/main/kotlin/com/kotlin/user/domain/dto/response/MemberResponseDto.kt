package com.kotlin.user.domain.dto.response

import com.kotlin.user.domain.entity.Member
import java.time.LocalDateTime

class MemberResponseDto(
        val memberId: Long,
        val email: String,
        val nickName: String,
        val regDate: LocalDateTime
) {

    companion object {
        fun mapToMemberResponse(member: Member) =
                MemberResponseDto(
                        memberId = member.memberId!!,
                        email = member.email,
                        nickName = member.nickname,
                        regDate = member.regDate!!
                )
    }

}