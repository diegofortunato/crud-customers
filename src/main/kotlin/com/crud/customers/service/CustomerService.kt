package com.crud.customers.service

import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.CustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface CustomerService {
    fun createCustomer(customerRequest: CustomerEntity, document: String): CustomerDTO
    fun findConsumer(paging: PageRequest, customerName: String, customerType: String): Page<CustomerDTO>
    fun deleteCustomer(customerID: Long)
    fun updateCustomer(customerID: Long, customer: CustomerEntity): CustomerDTO
}