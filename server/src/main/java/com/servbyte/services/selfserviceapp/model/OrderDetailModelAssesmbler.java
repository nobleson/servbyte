package com.servbyte.services.selfserviceapp.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.servbyte.services.selfserviceapp.controller.OrderDetailsController;



@Component
public class OrderDetailModelAssesmbler implements RepresentationModelAssembler<OrderDetails, EntityModel<OrderDetails>> {

  @Override
  public EntityModel<OrderDetails> toModel(OrderDetails orderDetails) {

    return EntityModel.of(orderDetails, //
        linkTo(methodOn(OrderDetailsController.class).one(orderDetails.getId())).withSelfRel(),
        linkTo(methodOn(OrderDetailsController.class).all()).withRel("orderdetails"));
  }
}