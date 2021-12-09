package com.arman.storeDetails.model

import javax.persistence.*

@Entity
class AddressPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long=0
    @Column
    var dateValidFrom:String?=null
    @Column
    var dateValidUntil:String?=null
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "fk_storeAddress_id")
    var addressStore : AddressStore?=null
}