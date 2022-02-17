package com.crud.customers.controller.request

import javax.validation.Valid
import javax.validation.constraints.NotNull

data class Request<T> (
    @field:NotNull(message = "Request cannot be null")
    @field:Valid
    var request: T
)