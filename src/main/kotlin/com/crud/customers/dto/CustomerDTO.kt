package com.crud.customers.dto

import com.crud.customers.constant.CustomerStatusEnum
import com.crud.customers.constant.CustomerTypeEnum
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CustomerDTO(
    @JsonProperty("id")
    val customerID: Long,

    @JsonProperty("document")
    @field:NotNull @field:NotEmpty(message = "Document cannot be empty")
    @field:Size(min = 14, max = 18, message = "The document must have at least 14 characters (CPF) and a maximum of 17 (CNPJ). EX: xxx.xxx.xxx-xx - xx.xxx.xxx/0001-xx")
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

    @JsonProperty("customer_pf")
    val customerIndividual: CustomerIndividualDTO?,

    @JsonProperty("customer_pj")
    val customerCorporate: CustomerCorporateDTO?,

    @JsonProperty("create_at")
    val createAt: LocalDate?,

    @JsonProperty("update_at")
    val updateAt: LocalDate?
)
