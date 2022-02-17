package com.crud.customers.entity

import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "customer_individual_tb")
data class CustomerIndividualEntity(
    @Id
    @Column(name = "customer_corporate_cpf")
    var cpf: String,

    @Column(name = "customer_individual_name")
    var fullName: String,

    @OneToOne(mappedBy = "customerIndividual")
    var customer: CustomerEntity?
)
