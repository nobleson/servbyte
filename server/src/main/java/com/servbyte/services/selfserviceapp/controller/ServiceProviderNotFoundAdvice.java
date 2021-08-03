package com.servbyte.services.selfserviceapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.servbyte.services.selfserviceapp.util.ServiceProviderNotFoundException;

@ControllerAdvice
class ServiceProviderNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(ServiceProviderNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String providerNotFoundHandler(ServiceProviderNotFoundException ex) {
    return ex.getMessage();
  }
}