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

import com.servbyte.services.selfserviceapp.jpa.CustomerRepository;
import com.servbyte.services.selfserviceapp.model.Customer;
import com.servbyte.services.selfserviceapp.model.CustomerModelAssembler;
import com.servbyte.services.selfserviceapp.util.CustomerNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class CustomerController {

  private final CustomerRepository repository;
  private final CustomerModelAssembler assembler;

  CustomerController(CustomerRepository repository, CustomerModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  
  
  // Aggregate root
  // end::get-aggregate-root[]
  
  @GetMapping("/customers")
  public CollectionModel<EntityModel<Customer>> all() {

    List<EntityModel<Customer>> customer = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(customer, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
  }  

  // Single item
  @GetMapping("/customers/{id}")
  public EntityModel<Customer> one(@PathVariable Long id) {

	  Customer customer = repository.findById(id) //
        .orElseThrow(() -> new CustomerNotFoundException(id));

    return assembler.toModel(customer);
  }
  
  @PostMapping("/customers")
  ResponseEntity<?> newCustomer(@RequestBody Customer newCustomer) {

    EntityModel<Customer> entityModel = assembler.toModel(repository.save(newCustomer));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  
  @PutMapping("/customer/{id}")
  ResponseEntity<?> replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

	  Customer updatedCustomer = repository.findById(id) //
        .map(customer -> {
        	customer.setFirstName(newCustomer.getFirstName());
        	customer.setLastName(newCustomer.getLastName());
        	customer.setPhoneNumber(newCustomer.getPhoneNumber());
        	customer.setEmailAddress(newCustomer.getEmailAddress());
        	customer.setContactAddress(newCustomer.getContactAddress());
        	customer.setCity(newCustomer.getCity());
          return repository.save(customer);
        }) //
        .orElseGet(() -> {
        	newCustomer.setId(id);
          return repository.save(newCustomer);
        });

    EntityModel<Customer> entityModel = assembler.toModel(updatedCustomer);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }
  
  @DeleteMapping("/customer/{id}")
  ResponseEntity<?> deleteCity(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
  
}