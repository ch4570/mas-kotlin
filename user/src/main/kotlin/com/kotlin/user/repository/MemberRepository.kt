package com.kotlin.user.repository

import com.kotlin.user.domain.entity.Member
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface MemberRepository : R2dbcRepository<Member, Long> {
}