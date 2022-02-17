package com.crud.customers.extension

import com.crud.customers.dto.AddressDTO
import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.AddressEntity
import com.crud.customers.entity.CustomerEntity

object EntityToDTOExtension {

    fun CustomerEntity.toDTO(document: String) = CustomerDTO(
        this.customerID!!,
        document,
        this.telephone,
        this.email,
        this.address!!.toDTO(),
        this.customerStatus,
        this.createAt,
        this.updateAt
    )

    fun AddressEntity.toDTO() = AddressDTO(
        this.addressID,
        this.street,
        this.district,
        this.city,
        this.state,
        this.cep
    )
}