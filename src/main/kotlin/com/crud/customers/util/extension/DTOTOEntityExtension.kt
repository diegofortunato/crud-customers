package com.crud.customers.util.extension

import com.crud.customers.dto.AddressDTO
import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.AddressEntity
import com.crud.customers.entity.CustomerEntity
import java.time.LocalDate

object DTOTOEntityExtension {

    fun CustomerDTO.toEntity() = CustomerEntity(
        this.customerID,
        this.telephone,
        this.email,
        this.customerStatus,
        this.address!!.toEntity(),
        null,
        null,
        this.createAt,
        LocalDate.now()
    )

    fun AddressDTO.toEntity() = AddressEntity(
        this.addressID,
        this.street,
        this.district,
        this.city,
        this.state,
        this.cep,
        null
    )
}