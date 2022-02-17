package com.crud.customers.service.impl

import com.crud.customers.constant.CustomerStatusEnum
import com.crud.customers.constant.CustomerTypeEnum
import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.CustomerCorporateEntity
import com.crud.customers.entity.CustomerEntity
import com.crud.customers.entity.CustomerIndividualEntity
import com.crud.customers.extension.EntityToDTOExtension.toDTO
import com.crud.customers.repository.CustomerCorporateRepository
import com.crud.customers.repository.CustomerIndividualRepository
import com.crud.customers.repository.CustomerRepository
import com.crud.customers.service.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    private val customerIndividualRepository: CustomerIndividualRepository,
    private val customerCorporateRepository: CustomerCorporateRepository,
) : CustomerService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createCustomer(customerRequest: CustomerEntity, document: String): CustomerDTO {
        log.info("Create Customer service")

        val numbersContainsCPF = 11
        val numbersContainsCNPJ = 14

        var customerIndividual = Optional.empty<CustomerIndividualEntity>()
        var customerCorporate = Optional.empty<CustomerCorporateEntity>()

        when (document.length) {
            numbersContainsCPF -> {
                customerIndividual = customerIndividualRepository.findById(document)
            }
            numbersContainsCNPJ -> {
                customerCorporate = customerCorporateRepository.findById(document)
            }
            else -> {
                // Lançar exceção
            }
        }

        val individual = if (customerIndividual.isPresent) customerIndividual.get() else null
        val corporate = if (customerCorporate.isPresent) customerCorporate.get() else null

        val customer = setValuesCustomer(customerRequest, individual, corporate)

        return customerRepository.save(customer).toDTO(document)
    }

    private fun setValuesCustomer(customerRequest: CustomerEntity, individual: CustomerIndividualEntity?, corporate: CustomerCorporateEntity?): CustomerEntity {
        return CustomerEntity(
            null,
            customerRequest.telephone,
            customerRequest.email,
            CustomerStatusEnum.ATIVE,
            customerRequest.address,
            individual,
            corporate,
            LocalDate.now(),
            LocalDate.now()
        )
    }
}