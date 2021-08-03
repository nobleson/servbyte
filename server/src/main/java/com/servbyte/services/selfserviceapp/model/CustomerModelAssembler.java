package com.servbyte.services.selfserviceapp.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.servbyte.services.selfserviceapp.controller.CustomerController;



@Component
public class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

  @Override
  public EntityModel<Customer> toModel(Customer customer) {

    return EntityModel.of(customer, //
        linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
        linkTo(methodOn(CustomerController.class).all()).withRel("customer"));
  }
}