package com.kotlin.user.exception

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException() {
}