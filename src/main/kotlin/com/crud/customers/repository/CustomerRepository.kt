package com.crud.customers.repository

import com.crud.customers.entity.CustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerRepository : JpaRepository<CustomerEntity, Long>{
    fun findAllByCustomerIndividualFullNameContaining(customerName: String, pageable: Pageable): Page<CustomerEntity>
    fun findAllByCustomerCorporateCompanyNameContaining(customerName: String, pageable: Pageable): Page<CustomerEntity>
    fun findByCustomerIndividualCpf(editedDocument: String) : Optional<CustomerEntity>
    fun findByCustomerCorporateCnpj(editedDocument: String) : Optional<CustomerEntity>
}