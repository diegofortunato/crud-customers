package com.crud.customers.extension

import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.CustomerEntity

object DTOTOEntityExtension {

    fun CustomerDTO.toEntity() = CustomerEntity(
        this.customerID,
        this.customerFullName
    )
}