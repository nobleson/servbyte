package com.servbyte.services.selfserviceapp.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.servbyte.services.selfserviceapp.controller.CityController;



@Component
public class CityModeAssembler implements RepresentationModelAssembler<City, EntityModel<City>> {

  @Override
  public EntityModel<City> toModel(City city) {

    return EntityModel.of(city, //
        linkTo(methodOn(CityController.class).one(city.getId())).withSelfRel(),
        linkTo(methodOn(CityController.class).all()).withRel("city"));
  }
}