package com.servbyte.services.selfserviceapp.model;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.servbyte.services.selfserviceapp.controller.ServiceProviderController;

@Component
public class ServiceProviderModelAssembler implements RepresentationModelAssembler<ServiceProvider, EntityModel<ServiceProvider>> {

  @Override
  public EntityModel<ServiceProvider> toModel(ServiceProvider provider) {

    return EntityModel.of(provider, //
        linkTo(methodOn(ServiceProviderController.class).one(provider.getId())).withSelfRel(),
        linkTo(methodOn(ServiceProviderController.class).all()).withRel("serviceprovider"));
  }
}