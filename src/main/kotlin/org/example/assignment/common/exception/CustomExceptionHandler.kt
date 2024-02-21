package org.example.assignment.common.exception

import org.example.assignment.common.dto.ApiResponse
import org.example.assignment.common.status.ResultCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.security.SignatureException

@RestControllerAdvice
class CustomExceptionHandler {

    // validator에서 발생한 에러들을 모두 처리한다
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    protected fun handleValidationExceptions(ex: MethodArgumentNotValidException):
//        ResponseEntity<ApiResponse<MutableMap<String, String>>> {
//
//        val errors = mutableMapOf<String, String>()
//        ex.bindingResult.allErrors.forEach { error ->
//            val fieldName = (error as FieldError).field
//            val errorMessage = error.getDefaultMessage()
//            errors[fieldName] = errorMessage ?: "Not Exception Message"
//        }
//        return ResponseEntity(ApiResponse(
//            ResultCode.ERROR.name,
//            errors,
//            ResultCode.ERROR.msg
//        ), HttpStatus.BAD_REQUEST)
//    }
//
//    // 사용자가 생성한 오류, 즉 입력 단계에서 발생한 에러(사용자가 만든 InvalidInputException)들을 처리한다
//    @ExceptionHandler(InvalidInputException::class)
//    protected fun invalidException(ex: InvalidInputException):
//            ResponseEntity<ApiResponse<Map<String, String>>> {
//        val errors = mapOf(ex.fieldName to (ex.message ?: "Not Exception Message"))
//        return ResponseEntity(ApiResponse(
//            ResultCode.ERROR.name,
//            errors,
//            ResultCode.ERROR.msg
//        ), HttpStatus.BAD_REQUEST)
//    }
//
//    @ExceptionHandler(SignatureException::class)
//    fun handleSignatureException() =
//        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 유효하지 않습니다.")
//
////    @ExceptionHandler(MalformedJwtException::class)
////    fun handleMalformedJwtException() =
////        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("올바르지 않은 토큰입니다.")
////
////    @ExceptionHandler(ExpiredJwtException::class)
////    fun handleExpiredJwtException() =
////        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 만료되었습니다. 다시 로그인해주세요.")
//
//    // 그 외의 모든 일반적인 에러를 처리한다
//    @ExceptionHandler(Exception::class)
//    protected fun defaultException(ex: Exception):
//            ResponseEntity<ApiResponse<Map<String, String>>> {
//        val errors = mapOf("미처리 에러" to (ex.message ?: "Not Exception Message"))
//        return ResponseEntity(ApiResponse(
//            ResultCode.ERROR.name,
//            errors,
//            ResultCode.ERROR.msg
//        ), HttpStatus.BAD_REQUEST)
//    }
}