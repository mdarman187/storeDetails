package com.arman.storeDetails.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class AddressPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
    @Column
    var dateValidFrom:LocalDate?=null
    @Column
    var dateValidUntil:LocalDate?=null
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "fk_storeAddress_id")
    var addressStore : AddressStore?=null
}