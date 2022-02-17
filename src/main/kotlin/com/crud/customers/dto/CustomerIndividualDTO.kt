package com.crud.customers.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CustomerIndividualDTO(
    @JsonProperty("cpf")
    var cpf: String,

    @JsonProperty("name")
    var fullName: String,
)
