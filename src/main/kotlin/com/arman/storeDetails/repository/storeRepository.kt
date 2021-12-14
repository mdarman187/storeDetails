package com.arman.storeDetails.repository

import com.arman.storeDetails.model.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate


interface StoreRepository : JpaRepository<Store, Long>{
    @Query("SELECT st FROM Store obj Join st.AddressPeriod ap where ((ap.dateValidFrom <= :refDate) and ((ap.dateValidUntil = null) or (ap.dateValidUntil >= :refDate))or (ap.dateValidUntil >= :refDate and :futureFlag=true))")
    fun getStore(@Param("refDate") refDate: LocalDate?, @Param ("futureFlag") futureFlag: Boolean): List<Any>

}