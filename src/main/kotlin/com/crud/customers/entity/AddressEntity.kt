package com.crud.customers.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "address_tb")
data class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    val addressID: Long,

    @Column(name = "address_street")
    var street: String,

    @Column(name = "address_district")
    var district: String,

    @Column(name = "address_city")
    var city: String,

    @Column(name = "address_state")
    var state: String,

    @Column(name = "address_cep")
    var cep: String,

    @OneToOne(mappedBy = "address")
    var customer: CustomerEntity?
)
