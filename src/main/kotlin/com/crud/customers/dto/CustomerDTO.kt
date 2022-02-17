package com.crud.customers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CustomerDTO(
    @JsonProperty("id")
    val customerID: Long,

    @JsonProperty("name")
    @field:NotNull @field:NotEmpty(message = "Full name cannot be empty")
    val customerFullName: String
)
