package com.crud.customers.controller.response

data class ResponsePagination<T>(
    var data: T,
    var totalPages: Int,
    var currentPage: Int,
    var totalItems: Long
)