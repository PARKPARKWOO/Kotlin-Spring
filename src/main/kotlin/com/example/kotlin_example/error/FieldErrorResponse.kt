package com.example.kotlin_example.error

import org.springframework.boot.context.properties.bind.BindResult
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.validation.BindingResult

class FieldErrorResponse(
    httpStatus: HttpStatusCode,
    message: String,
    var errors : List<FieldError> = ArrayList()
) {
    class FieldError private constructor(
        val field:String,
        val value:String,
        val reason: String?
    ){
        companion object{
            fun of(bindingResult: BindingResult): List<FieldError> {
                val fieldErrorResponse = bindingResult.fieldErrors
                return fieldErrorResponse.map {
                    FieldError(
                        field =  it.field,
                        value =  it.rejectedValue?.toString() ?: "",
                        reason = it.defaultMessage
                    )
                }
            }
        }
    }
}