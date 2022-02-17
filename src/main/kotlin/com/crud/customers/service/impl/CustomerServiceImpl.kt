package com.crud.customers.service.impl

import com.crud.customers.constant.APIConstant
import com.crud.customers.constant.CustomerStatusEnum
import com.crud.customers.dto.CustomerDTO
import com.crud.customers.entity.CustomerCorporateEntity
import com.crud.customers.entity.CustomerEntity
import com.crud.customers.entity.CustomerIndividualEntity
import com.crud.customers.util.extension.EntityToDTOExtension.toDTO
import com.crud.customers.repository.CustomerCorporateRepository
import com.crud.customers.repository.CustomerIndividualRepository
import com.crud.customers.repository.CustomerRepository
import com.crud.customers.service.CustomerService
import com.crud.customers.util.APIUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import javax.persistence.EntityNotFoundException

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    private val customerIndividualRepository: CustomerIndividualRepository,
    private val customerCorporateRepository: CustomerCorporateRepository,
) : CustomerService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun createCustomer(customerRequest: CustomerEntity, document: String): CustomerDTO {
        log.info("Create Customer service")

        val editedDocument = APIUtil.removeSpecialCaracters(document)

        val numbersContainsCPF = 11
        val numbersContainsCNPJ = 14

        var customerIndividual = Optional.empty<CustomerIndividualEntity>()
        var customerCorporate = Optional.empty<CustomerCorporateEntity>()

        when (editedDocument.length) {
            numbersContainsCPF -> {
                customerIndividual = customerIndividualRepository.findById(editedDocument)
            }
            numbersContainsCNPJ -> {
                customerCorporate = customerCorporateRepository.findById(editedDocument)
            }
            else -> {
                throw EntityNotFoundException(APIConstant.DETAILS_ERROR_404)
            }
        }

        verifyCustomers(customerIndividual, customerCorporate)

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

    private fun verifyCustomers(customerIndividual: Optional<CustomerIndividualEntity>,customerCorporate: Optional<CustomerCorporateEntity>) {
        if (customerIndividual.isEmpty && customerCorporate.isEmpty) throw EntityNotFoundException(APIConstant.DETAILS_ERROR_404)
    }
}