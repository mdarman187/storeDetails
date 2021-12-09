package com.arman.storeDetails.controller

import com.arman.storeDetails.model.Store
import com.arman.storeDetails.repository.StoreRepository
import com.arman.storeDetails.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.auditing.CurrentDateTimeProvider
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import com.arman.storeDetails.constants.*

@RestController
@RequestMapping(BASE_URI)
class StoreController {

    @Autowired
    lateinit var storeService: StoreService

    @Autowired
    lateinit var storeRepository: StoreRepository

    @PostMapping(ALL_STORES_DATA)
    fun save(@RequestBody store: Store):String{
        store.createdAt = LocalDateTime.now()
        store.lastUpdated = LocalDateTime.now()
        storeRepository.save(store)
        return DATA_SAVED
    }

    @GetMapping(ALL_STORES_DATA)
    fun getStore(): List<Store>{
        return storeService.getStores()
    }

    @GetMapping(STORE_BY_ID_DATA)
    fun getStoreById(@PathVariable storeId: Long): Any{

        return storeService.getStoreById(storeId)

    }

}