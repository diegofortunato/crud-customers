package com.crud.customers.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CustomerCorporateDTO(
    @JsonProperty("cnpj")
    var cnpj: String,

    @JsonProperty("company_name")
    var companyName: String,
)
