package com.crud.customers.dto

import com.crud.customers.constant.CustomerStatusEnum
import com.crud.customers.constant.CustomerTypeEnum
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CustomerDTO(
    @JsonProperty("id")
    val customerID: Long,

    @JsonProperty("document")
    @field:NotNull @field:NotEmpty(message = "Document cannot be empty")
    val document: String?,

    @JsonProperty("telephone")
    @field:NotNull @field:NotEmpty(message = "Telephone cannot be empty")
    val telephone: String?,

    @JsonProperty("email")
    @field:NotNull @field:NotEmpty(message = "Email cannot be empty")
    val email: String?,

    @JsonProperty("address")
    @field:NotNull (message = "Address cannot be empty")
    val address: AddressDTO?,

    @JsonProperty("status")
    val customerStatus: CustomerStatusEnum?,

    @JsonProperty("create_at")
    val createAt: LocalDate?,

    @JsonProperty("update_at")
    val updateAt: LocalDate?
)
