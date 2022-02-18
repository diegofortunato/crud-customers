package com.crud.customers.service

import com.crud.customers.constant.CustomerStatusEnum
import com.crud.customers.entity.AddressEntity
import com.crud.customers.entity.CustomerCorporateEntity
import com.crud.customers.entity.CustomerEntity
import com.crud.customers.entity.CustomerIndividualEntity
import com.crud.customers.repository.CustomerCorporateRepository
import com.crud.customers.repository.CustomerIndividualRepository
import com.crud.customers.repository.CustomerRepository
import com.crud.customers.service.impl.CustomerServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.time.LocalDate
import java.util.Optional
import javax.persistence.EntityExistsException
import javax.persistence.EntityNotFoundException

@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceImplTest {

    @MockBean
    private val customerRepository: CustomerRepository? = null

    @MockBean
    private val customerIndividualRepository: CustomerIndividualRepository? = null

    @MockBean
    private val customerCorporateRepository: CustomerCorporateRepository? = null

    @Autowired
    private val customerService: CustomerServiceImpl? = null

    @Test
    fun createCustomerPFWithSuccessTest() {
        given<Optional<CustomerEntity>>(customerRepository?.findByCustomerIndividualCpf("12345678910"))
            .willReturn(Optional.empty())

        given<Optional<CustomerIndividualEntity>>(customerIndividualRepository?.findById("12345678910"))
            .willReturn(Optional.of(getCustomerIndividual()))

        given<CustomerEntity>(customerRepository?.save(anyObject()))
            .willReturn(getCustomer())

        val response = customerService!!.createCustomer(getCustomer(),"123.456.789-10")
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.customerID, 1L)
    }

    @Test
    fun createCustomerPJWithSuccessTest() {
        given<Optional<CustomerEntity>>(customerRepository?.findByCustomerCorporateCnpj("12345678900011"))
            .willReturn(Optional.empty())

        given<Optional<CustomerCorporateEntity>>(customerCorporateRepository?.findById("12345678900011"))
            .willReturn(Optional.of(getCustomerCorporate()))

        given<CustomerEntity>(customerRepository?.save(anyObject()))
            .willReturn(getCustomer())

        val response = customerService!!.createCustomer(getCustomer(),"123.456.789/0001-1")
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.customerID, 1L)
    }

    @Test
    fun createCustomerWithFailedTest() {
        given<Optional<CustomerEntity>>(customerRepository?.findByCustomerIndividualCpf("12345678910"))
            .willReturn(Optional.of(getCustomer()))

        Assertions.assertThrows(
            EntityExistsException::class.java
        ) { customerService!!.createCustomer(getCustomer(), "123.456.789-10") }
    }

    @Test
    fun findAllCustomerWithSuccessAndWithoutParametersTest() {
        given<Page<CustomerEntity>>(customerRepository?.findAll(PageRequest.of(0, 10)))
            .willReturn(PageImpl(getCustomerList()))

        val response = customerService!!.findConsumer(PageRequest.of(0, 10), "", "")
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.size, 1)
    }

    @Test
    fun findAllCustomerWithSuccessAndWithParametersPFTest() {
        given<Page<CustomerEntity>>(customerRepository?.findAllByCustomerIndividualFullNameContaining("Diego", PageRequest.of(0, 10)))
            .willReturn(PageImpl(getCustomerPFList()))

        val response = customerService!!.findConsumer(PageRequest.of(0, 10), "Diego", "PF")
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.size, 1)
    }

    @Test
    fun findAllCustomerWithSuccessAndWithParametersPJTest() {
        given<Page<CustomerEntity>>(customerRepository?.findAllByCustomerCorporateCompanyNameContaining("Supermercado", PageRequest.of(0, 10)))
            .willReturn(PageImpl(getCustomerPJList()))

        val response = customerService!!.findConsumer(PageRequest.of(0, 10), "Supermercado", "PJ")
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.size, 1)
    }

    @Test
    fun deleteCustomerWithSuccessTest() {
        given<Optional<CustomerEntity>>(customerRepository?.findById(1L))
            .willReturn(Optional.of(getCustomer()))

        doNothing().`when`(customerRepository)!!.delete(getCustomer())

        customerService!!.deleteCustomer(1L)

        verify(customerRepository, times(1))!!.delete(getCustomer())
    }

    @Test
    fun deleteCustomerNotFoundTest() {
        given<Optional<CustomerEntity>>(customerRepository?.findById(2L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { customerService!!.deleteCustomer(2L) }
    }

    @Test
    fun updateResellerWithSuccessTest() {
        given<Optional<CustomerEntity>>(customerRepository?.findById(1L))
            .willReturn(Optional.of(getCustomer()))

        given<CustomerEntity>(customerRepository?.save(anyObject()))
            .willReturn(getCustomerUpdate())

        val response = customerService!!.updateCustomer(1L, getCustomerUpdate())
        Assertions.assertNotNull(response)
        Assertions.assertEquals(response.telephone, "19994778820")
    }

    @Test
    fun updateResellerNotFoundTest() {
        given<Optional<CustomerEntity>>(customerRepository?.findById(1L))
            .willReturn(Optional.empty())

        Assertions.assertThrows(
            EntityNotFoundException::class.java
        ) { customerService!!.updateCustomer(2L, getCustomer()) }
    }

    private fun getCustomer(): CustomerEntity {
        return CustomerEntity(
            1L,
            "19994778822",
            "diego@gmail.com",
            CustomerStatusEnum.ATIVE,
            getAddress(),
            getCustomerIndividual(),
            null,
            LocalDate.now(),
            LocalDate.now()
        )
    }

    private fun getCustomerList(): List<CustomerEntity> {
        return listOf(CustomerEntity(
            1L,
            "19994778820",
            "diego@gmail.com",
            CustomerStatusEnum.ATIVE,
            getAddress(),
            getCustomerIndividual(),
            null,
            LocalDate.now(),
            LocalDate.now()
        ))
    }

    private fun getCustomerPFList(): List<CustomerEntity> {
        return listOf(CustomerEntity(
            1L,
            "19994778822",
            "diego@gmail.com",
            CustomerStatusEnum.ATIVE,
            getAddress(),
            getCustomerIndividual(),
            null,
            LocalDate.now(),
            LocalDate.now()
        ))
    }

    private fun getCustomerPJList(): List<CustomerEntity> {
        return listOf(CustomerEntity(
            1L,
            "19994778822",
            "diego@gmail.com",
            CustomerStatusEnum.ATIVE,
            getAddress(),
            null,
            getCustomerCorporate(),
            LocalDate.now(),
            LocalDate.now()
        ))
    }

    private fun getCustomerUpdate(): CustomerEntity {
        return CustomerEntity(
            1L,
            "19994778820",
            "diego@gmail.com",
            CustomerStatusEnum.ATIVE,
            getAddress(),
            getCustomerIndividual(),
            null,
            LocalDate.now(),
            LocalDate.now()
        )
    }

    private fun getAddress(): AddressEntity {
        return AddressEntity(
            1L,
            "Rua Glicerio",
            "liberdade",
            "São Paulo",
            "SP",
            "012548448",
            null
        )
    }

    private fun getCustomerIndividual(): CustomerIndividualEntity {
        return CustomerIndividualEntity(
            "12345678910",
            "Diego Fortunato",
            null
        )
    }

    private fun getCustomerCorporate(): CustomerCorporateEntity {
        return CustomerCorporateEntity(
            "12345678900011",
            "Supermercado",
            null
        )
    }

    private fun <T> anyObject(): T {
        return Mockito.any()
    }
}