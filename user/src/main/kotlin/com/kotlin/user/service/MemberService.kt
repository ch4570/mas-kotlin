package com.kotlin.user.service

import com.kotlin.user.domain.entity.Member
import com.kotlin.user.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
        private val memberRepository: MemberRepository
) {

    fun saveMember(member: Member) =
            memberRepository.save(member).subscribe()

    fun findAllMember() =
            memberRepository.findAll()


    fun findMemberByMemberId(memberId: Long) =
            memberRepository.findById(memberId)

    fun removeMember(memberId: Long) =
            memberRepository.deleteById(memberId)
}