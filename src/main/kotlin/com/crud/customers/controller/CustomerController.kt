package com.crud.customers.controller

import com.crud.customers.constant.APIConstant
import com.crud.customers.controller.request.Request
import com.crud.customers.controller.response.Response
import com.crud.customers.controller.response.ResponsePagination
import com.crud.customers.dto.CustomerDTO
import com.crud.customers.util.extension.DTOTOEntityExtension.toEntity
import com.crud.customers.service.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.URLEncoder
import javax.validation.Valid

@RestController
@RequestMapping(value = [APIConstant.BASE_API])
class CustomerController(private val customerService: CustomerService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping(APIConstant.SERVICE_CREATE_CUSTOMER)
    fun createCustomer(@Valid @RequestBody customerRequest: Request<CustomerDTO>): ResponseEntity<Response<CustomerDTO>> {
        log.info("POST ${APIConstant.SERVICE_CREATE_CUSTOMER} for customer {}", customerRequest.request)

        val customer = customerService.createCustomer(
            customerRequest.request.toEntity(),
            customerRequest.request.document!!
        )
        return ResponseEntity
            .created(
                URI.create(
                URLEncoder.encode(APIConstant.BASE_API + APIConstant.SERVICE_GET_CUSTOMER, "UTF-8")))
            .body(Response(data = customer))
    }

    @GetMapping(APIConstant.SERVICE_GET_ALL_CUSTOMER)
    fun findAllConsumers(
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "size", defaultValue = "10") size: Int
    ): ResponseEntity<ResponsePagination<List<CustomerDTO>>> {
        log.info("GET ALL ${APIConstant.SERVICE_GET_ALL_CUSTOMER}")

        val paging = PageRequest.of(page, size)

        val response = customerService.findAllConsumer(paging)
        return ResponseEntity.ok(
            ResponsePagination(
                data = response.content,
                totalPages = response.totalPages,
                currentPage = response.number,
                totalItems = response.totalElements
            )
        )
    }

    @PatchMapping(APIConstant.SERVICE_UPDATE_CUSTOMER)
    fun updateCustomer(@PathVariable("id") customerID: Long, @Valid @RequestBody customerRequest: Request<CustomerDTO>):
            ResponseEntity<Response<CustomerDTO>> {
        log.info("PATCH ${APIConstant.SERVICE_UPDATE_CUSTOMER}")

        val customer = customerService.updateCustomer(customerID, customerRequest.request.toEntity())
        return ResponseEntity.ok(Response(data = customer))
    }

    @DeleteMapping(APIConstant.SERVICE_DELETE_CUSTOMER)
    fun deleteCustomer(@PathVariable("id") customerID: Long): ResponseEntity<Response<CustomerDTO>> {
        log.info("DELETE ${APIConstant.SERVICE_DELETE_CUSTOMER} for ID {}", customerID)

        customerService.deleteCustomer(customerID)
        return ResponseEntity.noContent().build()
    }
}