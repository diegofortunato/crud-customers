package com.crud.customers.service.impl

import com.crud.customers.constant.APIConstant
import com.crud.customers.constant.CustomerStatusEnum
import com.crud.customers.constant.CustomerTypeEnum
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
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import javax.persistence.EntityExistsException
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
                verifyCustomerExists(editedDocument, CustomerTypeEnum.PF)
                customerIndividual = customerIndividualRepository.findById(editedDocument)
            }
            numbersContainsCNPJ -> {
                verifyCustomerExists(editedDocument, CustomerTypeEnum.PJ)
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

    override fun findConsumer(paging: PageRequest, customerName: String, customerType: String): Page<CustomerDTO> {
        log.info("Find All Customer service.")

        if(customerType != "" && CustomerTypeEnum.PF.value == customerType.uppercase()){
            return customerRepository.findAllByCustomerIndividualFullNameContaining(customerName, paging).map { customer -> customer.toDTO(null) }
        }else if(customerType != "" && CustomerTypeEnum.PJ.value == customerType.uppercase()){
            return customerRepository.findAllByCustomerCorporateCompanyNameContaining(customerName, paging).map { customer -> customer.toDTO(null) }
        }

        return customerRepository.findAll(paging).map { customer -> customer.toDTO(null) }
    }

    override fun deleteCustomer(customerID: Long) {
        log.info("Delete Customer service")

        val customerDB = customerRepository.findById(customerID)
            .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404) }

        customerRepository.delete(customerDB)
    }

    override fun updateCustomer(customerID: Long, customer: CustomerEntity): CustomerDTO {
        log.info("Update Customer service")

        val customerDB = customerRepository.findById(customerID)
            .orElseThrow { EntityNotFoundException(APIConstant.ERROR_404) }

        updateFieldsCustomer(customerDB, customer)

        return customerRepository.save(customerDB).toDTO(null)
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

    private fun updateFieldsCustomer(customerDB: CustomerEntity?, customerRequest: CustomerEntity) {
        customerDB!!.telephone = customerRequest.telephone
        customerDB.email = customerRequest.email
        customerDB.address = customerRequest.address
    }

    private fun verifyCustomers(customerIndividual: Optional<CustomerIndividualEntity>,customerCorporate: Optional<CustomerCorporateEntity>) {
        if (customerIndividual.isEmpty && customerCorporate.isEmpty) throw EntityNotFoundException(APIConstant.DETAILS_ERROR_404)
    }

    private fun verifyCustomerExists(editedDocument: String, customerType: CustomerTypeEnum) {
        if(customerType == CustomerTypeEnum.PF){
            if (customerRepository.findByCustomerIndividualCpf(editedDocument).isPresent)
                throw EntityExistsException(APIConstant.ERROR_400)
        }else{
            if (customerRepository.findByCustomerCorporateCnpj(editedDocument).isPresent)
                throw EntityExistsException(APIConstant.ERROR_400)
        }
    }
}