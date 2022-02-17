package com.crud.customers.util.extension

import com.crud.customers.dto.AddressDTO
import com.crud.customers.dto.CustomerCorporateDTO
import com.crud.customers.dto.CustomerDTO
import com.crud.customers.dto.CustomerIndividualDTO
import com.crud.customers.entity.AddressEntity
import com.crud.customers.entity.CustomerCorporateEntity
import com.crud.customers.entity.CustomerEntity
import com.crud.customers.entity.CustomerIndividualEntity

object EntityToDTOExtension {

    fun CustomerEntity.toDTO(document: String?) = CustomerDTO(
        this.customerID!!,
        document,
        this.telephone,
        this.email,
        this.address!!.toDTO(),
        this.customerStatus,
        this.customerIndividual?.toDTO(),
        this.customerCorporate?.toDTO(),
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

    fun CustomerIndividualEntity.toDTO() = CustomerIndividualDTO(
        this.cpf,
        this.fullName
    )

    fun CustomerCorporateEntity.toDTO() = CustomerCorporateDTO(
        this.cnpj,
        this.companyName
    )
}