package com.crud.customers.entity

import com.crud.customers.constant.CustomerStatusEnum
import com.crud.customers.constant.CustomerTypeEnum
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.DiscriminatorColumn
import javax.persistence.DiscriminatorType
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "customer_tb")
data class CustomerEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id")
    var customerID: Long?,

    @Column(name = "customer_telephone")
    var telephone: String?,

    @Column(name = "customer_email")
    var email: String?,

    @Column(name = "customer_status")
    @Enumerated(EnumType.STRING)
    var customerStatus: CustomerStatusEnum?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    var address: AddressEntity?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_corporate_cpf", referencedColumnName = "customer_corporate_cpf")
    var customerIndividual: CustomerIndividualEntity?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_corporate_cnpj", referencedColumnName = "customer_corporate_cnpj")
    var customerCorporate: CustomerCorporateEntity?,

    @Column(name = "customer_create_at")
    var createAt: LocalDate?,

    @Column(name = "customer_update_at")
    var updateAt: LocalDate?,
)