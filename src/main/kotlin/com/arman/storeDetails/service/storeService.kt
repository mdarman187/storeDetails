package com.arman.storeDetails.service

import com.arman.storeDetails.constants.DATA_SAVED
import com.arman.storeDetails.exceptions.NullFieldException
import com.arman.storeDetails.model.Store
import com.arman.storeDetails.repository.StoreRepository
import com.arman.storeDetails.validation.Validation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class StoreService {

    @Autowired
    lateinit var storeRepository: StoreRepository

    @Autowired
    lateinit var validation: Validation

    fun getStores(refDate:String?,FutureFlag:Boolean): List<Any>
    {
        val date: LocalDate? = validation.validDateFormat(refDate)
        return storeRepository.getStore(date, FutureFlag)
    }

    fun getStoreById(storeId: Long): Store{
        return storeRepository.getById(storeId)
    }

    fun addStore(store: Store): String {
        store.createdAt = LocalDateTime.now()
        store.lastUpdated = LocalDateTime.now()
        if( validation.validData(store))
        {
            storeRepository.save(store)
            return DATA_SAVED
        }
        else
        {  throw NullFieldException()
        }
    }
}