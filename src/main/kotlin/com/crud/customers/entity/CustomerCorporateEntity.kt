package com.crud.customers.entity

import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "customer_corporate_tb")
class CustomerCorporateEntity(
    @Id
    @Column(name = "customer_corporate_cnpj")
    var cnpj: String,

    @Column(name = "customer_corporate_company_name")
    var companyName: String,

    @OneToOne(mappedBy = "customerCorporate")
    var customer: CustomerEntity?
)
