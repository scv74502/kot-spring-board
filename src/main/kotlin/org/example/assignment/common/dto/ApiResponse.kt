package org.example.assignment.common.dto


/*
    Api의 응답 처리를 공용으로 하기  위한 클래스
* */
data class ApiResponse (
    val status: ApiStatus,
    val message: String?,
    val data: Any?
) {
    companion object {
        fun success(data: Any?): ApiResponse {
            return ApiResponse(ApiStatus.SUCCESS, null, data)
        }

        fun error(message: String?): ApiResponse {
            return ApiResponse(ApiStatus.ERROR, message, null)
        }
    }
}

enum class ApiStatus{
    SUCCESS,
    ERROR
}