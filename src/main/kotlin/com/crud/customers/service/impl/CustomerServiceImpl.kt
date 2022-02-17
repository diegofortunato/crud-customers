package com.crud.customers.service.impl

import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.CustomerEntity
import com.crud.customers.extension.EntityToDTOExtension.toDTO
import com.crud.customers.repository.CustomerRepository
import com.crud.customers.service.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createCustomer(customer: CustomerEntity): CustomerDTO {
        log.info("Create Customer service")

        return customerRepository.save(customer).toDTO()
    }
}