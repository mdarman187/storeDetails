package com.arman.storeDetails.repository

import com.arman.storeDetails.model.Store
import org.springframework.data.jpa.repository.JpaRepository


interface StoreRepository : JpaRepository<Store, Long>{
}