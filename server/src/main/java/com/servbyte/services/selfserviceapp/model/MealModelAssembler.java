package com.servbyte.services.selfserviceapp.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.servbyte.services.selfserviceapp.controller.MealController;


@Component
public class MealModelAssembler implements RepresentationModelAssembler<Meal, EntityModel<Meal>> {

  @Override
  public EntityModel<Meal> toModel(Meal meal) {

    return EntityModel.of(meal, //
        linkTo(methodOn(MealController.class).one(meal.getId())).withSelfRel(),
        linkTo(methodOn(MealController.class).all()).withRel("meal"));
  }
}