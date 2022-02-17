package com.crud.customers.extension

import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.CustomerEntity

object EntityToDTOExtension {

    fun CustomerEntity.toDTO() = CustomerDTO(
        this.customerID,
        this.customerFullName
    )
}