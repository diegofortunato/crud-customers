package com.crud.customers.service

import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.CustomerEntity

interface CustomerService {

    fun createCustomer(customer: CustomerEntity): CustomerDTO
}