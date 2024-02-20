package org.example.assignment.common.exception

//exception for error at field value except @Valid
class InvalidInputException (
    val fieldName: String = "",
    message: String = "Invalid Input"
) : RuntimeException(message)