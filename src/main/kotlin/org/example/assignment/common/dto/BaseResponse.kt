package org.example.assignment.common.dto

import org.example.assignment.common.status.ResultCode

data class BaseResponse<T> (
    val resultCode: String = ResultCode.SUCCESS.name,   // result code
    val data: T ?= null,    // data with addressed result value, for return
    val message: String = ResultCode.SUCCESS.msg    // process message
)