package com.servbyte.services.selfserviceapp.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.servbyte.services.selfserviceapp.controller.PaymentController;



@Component
public class PaymentModelAssembler implements RepresentationModelAssembler<Payment, EntityModel<Payment>> {

  @Override
  public EntityModel<Payment> toModel(Payment meal) {

    return EntityModel.of(meal, //
        linkTo(methodOn(PaymentController.class).one(meal.getId())).withSelfRel(),
        linkTo(methodOn(PaymentController.class).all()).withRel("payment"));
  }
}