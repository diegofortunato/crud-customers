package com.crud.customers.repository

import com.crud.customers.entity.CustomerEntity
import com.crud.customers.entity.CustomerIndividualEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CustomerIndividualRepository : JpaRepository<CustomerIndividualEntity, String>