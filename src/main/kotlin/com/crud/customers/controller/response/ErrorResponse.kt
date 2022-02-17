package com.crud.customers.controller.response

data class ErrorResponse(
    val date: String,
    val code: Int,
    val error: String,
    val description: String
)

