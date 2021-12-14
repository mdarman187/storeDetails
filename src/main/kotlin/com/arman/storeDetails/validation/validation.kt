package com.arman.storeDetails.validation

import com.arman.storeDetails.exceptions.DataExistException
import com.arman.storeDetails.model.AddressPeriod
import com.arman.storeDetails.model.Store
import com.arman.storeDetails.repository.StoreRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class Validation  (val storeRepository: StoreRepository){
    fun validData(store: Store):Boolean
    {  val addressPeriod:List<AddressPeriod>? =store.addressPeriod
        if(store.name==null||store.status==null||store.addressPeriod!!.isEmpty())

        {
            return false
        }
        for(data in addressPeriod!!) {
            if(data.addressStore==null)
            {
                return false
            }

            if(data.dateValidFrom==null||data.addressStore!!.street==null||data.addressStore!!.houseNumber==null||data.addressStore!!.houseNumberSuffix==null||data.addressStore!!.postalCode==null||data.addressStore!!.city==null||data.addressStore!!.country==null)
            {
                return false
            }
        }
        var result=storeRepository.findAll()
        for(data in result)
        {
            if(data.name==store.name)
            {
                throw DataExistException(store.name!!)

            }
        }
        return true
    }

    fun validDateFormat(refDate: String?): LocalDate? {
        return if (refDate != null) {
            LocalDate.parse(refDate, DateTimeFormatter.ISO_DATE)
        }
        else {
            LocalDate.now()
        }
    }
}