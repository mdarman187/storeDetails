package com.arman.storeDetails.controller

import com.arman.storeDetails.model.Store
import com.arman.storeDetails.repository.StoreRepository
import com.arman.storeDetails.service.StoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.auditing.CurrentDateTimeProvider
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("store-service/v1")
class StoreController {

    @Autowired
    lateinit var storeService: StoreService

    @Autowired
    lateinit var storeRepository: StoreRepository

    @PostMapping("/stores")
    fun save(@RequestBody store: Store):String{
        store.createdAt = LocalDateTime.now()
        store.lastUpdated = LocalDateTime.now()
        storeRepository.save(store)
        return "Data Successfully Saved"
    }

    @GetMapping("/stores")
    fun getStore(): List<Store>{
        return storeService.getStores()
    }

    @GetMapping("/stores/{shopId}")
    fun getStoreById(@PathVariable storeId: Long): Any{

        return storeService.getStoreById(storeId)

    }

}