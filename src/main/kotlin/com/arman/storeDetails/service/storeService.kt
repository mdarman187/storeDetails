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

    fun getStores(refDate:String?,FutureFlag:Boolean): List<Store>{

        val date:LocalDate?=validation.validDateFormat(refDate)
        var result= storeRepository.findAll()

        if(result.isEmpty())
        {
            throw NoSuchElementException()
        }
        return if(date==null) {
            result
        } else if (FutureFlag) {
            futureData(result, date)
        } else {
            presentData(result, date)
        }
    }

    fun presentData(result: List<Store>, date: LocalDate): List<Store>{

        for(data in result)
        {
            data.addressPeriod=data.addressPeriod!!.filter{filterData->(filterData.dateValidFrom!! <=date&&(filterData.dateValidUntil==null||filterData.dateValidUntil!!>=date)||(filterData.dateValidFrom!! >=date&&(filterData.dateValidUntil==null||filterData.dateValidUntil!!>=date)))}
        }

        return result
    }

    fun futureData(result: List<Store>, date: LocalDate): List<Store>{

        for(data in result)
        {
            data.addressPeriod=data.addressPeriod!!.filter{filterData->(filterData.dateValidFrom!! <=date&&(filterData.dateValidUntil==null||filterData.dateValidUntil!!>=date)||(filterData.dateValidFrom!! >=date&&(filterData.dateValidUntil==null||filterData.dateValidUntil!!>=date)))}
        }

        return result
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