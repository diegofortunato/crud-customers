package com.crud.customers.dto

import com.crud.customers.entity.CustomerEntity
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class AddressDTO(
    @JsonProperty("id")
    val addressID: Long,

    @JsonProperty("street")
    @field:NotNull @field:NotEmpty(message = "Street cannot be empty")
    var street: String,

    @JsonProperty("district")
    @field:NotNull @field:NotEmpty(message = "District cannot be empty")
    var district: String,

    @JsonProperty("city")
    @field:NotNull @field:NotEmpty(message = "City cannot be empty")
    var city: String,

    @JsonProperty("state")
    @field:NotNull @field:NotEmpty(message = "State cannot be empty")
    var state: String,

    @JsonProperty("cep")
    @field:NotNull @field:NotEmpty(message = "CEP cannot be empty")
    var cep: String
)
