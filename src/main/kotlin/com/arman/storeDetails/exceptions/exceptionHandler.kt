package com.arman.storeDetails.exceptions

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import java.time.format.DateTimeParseException
import org.springframework.web.bind.annotation.ExceptionHandler




@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(): ResponseEntity<String> {
        return ResponseEntity("No values present in DB.", HttpStatus.NOT_FOUND)
    }



    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(): ResponseEntity<String> {
        return ResponseEntity("Invalid Data Input.", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DateTimeParseException::class)
    fun handleDateTimeParseException(): ResponseEntity<String> {
        return ResponseEntity("Incorrect Date Format, Please Follow this format: yyyy-mm-dd format.", HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataExistException::class)
    fun handleDataExistException( ex:DataExistException ): ResponseEntity<String>{
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)

    }
    @ExceptionHandler(NullFieldException::class)
    fun handleNullException( ex:NullFieldException): ResponseEntity<String>{
        return ResponseEntity(ex.message,HttpStatus.BAD_REQUEST)

    }


    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun handleNoMatchingDataException( ): ResponseEntity<String>{
        return ResponseEntity("Store with the given ID not present in the Records.",HttpStatus.BAD_REQUEST)

    }
}