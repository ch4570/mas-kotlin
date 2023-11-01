package com.kotlin.board.exception

class CustomException(
       val errorCode: ErrorCode
) : RuntimeException() {

}