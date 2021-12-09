package com.arman.storeDetails.service

import com.arman.storeDetails.model.Store
import com.arman.storeDetails.repository.StoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StoreService {

    @Autowired
    lateinit var storeRepository: StoreRepository

    fun getStores(): List<Store>{
        return storeRepository.findAll()
    }

    fun getStoreById(storeId: Long): Store{
        return storeRepository.getById(storeId)
    }

}