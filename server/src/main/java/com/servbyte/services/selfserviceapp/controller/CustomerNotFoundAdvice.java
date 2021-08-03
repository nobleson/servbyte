package com.servbyte.services.selfserviceapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.servbyte.services.selfserviceapp.util.CustomerNotFoundException;



@ControllerAdvice
class CustomerNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(CustomerNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String cityNotFoundHandler(CustomerNotFoundException ex) {
    return ex.getMessage();
  }
}