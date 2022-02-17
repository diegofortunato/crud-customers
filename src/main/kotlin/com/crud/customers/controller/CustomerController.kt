package com.crud.customers.controller

import com.crud.customers.constant.APIConstant
import com.crud.customers.controller.request.Request
import com.crud.customers.controller.response.Response
import com.crud.customers.dto.CustomerDTO
import com.crud.customers.util.extension.DTOTOEntityExtension.toEntity
import com.crud.customers.service.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
}