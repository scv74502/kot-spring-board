package org.example.assignment.common.status

enum class Gender(val desc: String) {
    MAN("male"),
    WOMAN("female")
}

enum class ResultCode(val msg: String){
    SUCCESS("successfully processed"),
    ERROR("Error occured")
}