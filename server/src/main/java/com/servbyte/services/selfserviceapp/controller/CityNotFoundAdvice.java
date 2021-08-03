package com.servbyte.services.selfserviceapp.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.servbyte.services.selfserviceapp.util.CityNotFoundException;


@ControllerAdvice
class CityNotFoundAdvice {

  @ResponseBody
  @ExceptionHandler(CityNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String cityNotFoundHandler(CityNotFoundException ex) {
    return ex.getMessage();
  }
}