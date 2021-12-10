package com.arman.storeDetails.exceptions

class DataExistException (val storeName:String ) :Exception("$storeName already exists.")