package com.kotlin.user.domain.entity

import com.kotlin.user.domain.dto.request.MemberRequestDto
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("MEMBER")
class Member(
        @Id
        @Column("MEMBER_ID")
        var memberId: Long? = null,

        @Column("EMAIL")
        var email: String,

        @Column("PASSWORD")
        var password: String,

        @Column("NICKNAME")
        var nickname: String,

        @Column("REG_DATE")
        @CreatedDate
        var regDate: LocalDateTime? = null,

        @Column("MOD_DATE")
        @LastModifiedDate
        var modDate: LocalDateTime? = null
) {

    fun updateMember(memberRequestDto: MemberRequestDto) : Member {
        email = memberRequestDto.email!!
        password = memberRequestDto.password!!
        nickname = memberRequestDto.nickname!!
        return this
    }

    override fun toString(): String {
        return "Member(memberId=$memberId, email='$email', password='$password', nickname='$nickname', regDate=$regDate, modDate=$modDate)"
    }
}