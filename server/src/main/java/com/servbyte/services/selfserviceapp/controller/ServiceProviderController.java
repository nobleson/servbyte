package com.servbyte.services.selfserviceapp.controller;


import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.servbyte.services.selfserviceapp.jpa.ServiceProviderReposistory;
import com.servbyte.services.selfserviceapp.model.ServiceProvider;
import com.servbyte.services.selfserviceapp.model.ServiceProviderModelAssembler;
import com.servbyte.services.selfserviceapp.util.ServiceProviderNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class ServiceProviderController {

  private final ServiceProviderReposistory repository;
  private final ServiceProviderModelAssembler assembler;

  ServiceProviderController(ServiceProviderReposistory repository, ServiceProviderModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  
  
  // Aggregate root
  // end::get-aggregate-root[]
  
  @GetMapping("/service-providers")
  public CollectionModel<EntityModel<ServiceProvider>> all() {

    List<EntityModel<ServiceProvider>> providers = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(providers, linkTo(methodOn(ServiceProviderController.class).all()).withSelfRel());
  }  

  // Single item
  @GetMapping("/service-providers/{id}")
  public EntityModel<ServiceProvider> one(@PathVariable Long id) {

	  ServiceProvider provider = repository.findById(id) //
        .orElseThrow(() -> new ServiceProviderNotFoundException(id));

    return assembler.toModel(provider);
  }
  
  @PostMapping("/service-providers")
  ResponseEntity<?> newServiceProvider(@RequestBody ServiceProvider newProvider) {

    EntityModel<ServiceProvider> entityModel = assembler.toModel(repository.save(newProvider));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  
  @PutMapping("/service-providers/{id}")
  ResponseEntity<?> replaceProvider(@RequestBody ServiceProvider newProvider, @PathVariable Long id) {

	  ServiceProvider updatedServiceProvider = repository.findById(id) //
        .map(serviceProvider -> {
      	  serviceProvider.setName(newProvider.getName());
      	  serviceProvider.setPhoneNumber(newProvider.getPhoneNumber());
      	  serviceProvider.setEmail(newProvider.getEmail());
      	  serviceProvider.setCity(newProvider.getCity());
      	  serviceProvider.setLogo(newProvider.getLogo());
          return repository.save(serviceProvider);
        }) //
        .orElseGet(() -> {
          newProvider.setId(id);
          return repository.save(newProvider);
        });

    EntityModel<ServiceProvider> entityModel = assembler.toModel(updatedServiceProvider);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }
  
  @DeleteMapping("/service-provider/{id}")
  ResponseEntity<?> deleteServiceProvider(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
  
}
