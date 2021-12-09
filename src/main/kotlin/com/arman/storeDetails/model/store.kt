package com.arman.storeDetails.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Store (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long= 0,

    @Column
    var name: String?= null,

    @Column
    var status: String?= null,

    @Column
    @CreatedDate
    var createdAt: LocalDateTime?= null,

    @Column
    @LastModifiedDate
    var lastUpdated: LocalDateTime?= null,

    @OneToMany(cascade=[CascadeType.ALL])
    @JoinColumn(name = "store_id")
    var addressPeriod : List<AddressPeriod>?= null

)