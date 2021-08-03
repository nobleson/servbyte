package com.servbyte.services.selfserviceapp.controller;


import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.servbyte.services.selfserviceapp.jpa.OrderDetailsRepository;
import com.servbyte.services.selfserviceapp.model.OrderDetailModelAssesmbler;
import com.servbyte.services.selfserviceapp.model.OrderDetails;
import com.servbyte.services.selfserviceapp.util.OrderDetailsNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;


@RestController
public class OrderDetailsController {

  private final OrderDetailsRepository repository;
  private final OrderDetailModelAssesmbler assembler;

  OrderDetailsController(OrderDetailsRepository repository, OrderDetailModelAssesmbler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }
  
  // Aggregate root
  // end::get-aggregate-root[]
  
  @GetMapping("/order-details")
  public CollectionModel<EntityModel<OrderDetails>> all() {

    List<EntityModel<OrderDetails>> orderDetails = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(orderDetails, linkTo(methodOn(OrderDetailsController.class).all()).withSelfRel());
  }  

  // Single item
  @GetMapping("/order-details/{id}")
  public EntityModel<OrderDetails> one(@PathVariable Long id) {

	  OrderDetails orderDetails = repository.findById(id) //
        .orElseThrow(() -> new OrderDetailsNotFoundException(id));

    return assembler.toModel(orderDetails);
  }
  
  @PostMapping("/order-details")
  ResponseEntity<?> newOrderDetails(@RequestBody OrderDetails newOrderDetails) {

    EntityModel<OrderDetails> entityModel = assembler.toModel(repository.save(newOrderDetails));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  
  @PutMapping("/order-details/{id}")
  ResponseEntity<?> replaceCity(@RequestBody OrderDetails newOrderDetails, @PathVariable Long id) {

	  OrderDetails updatedOrderDetails = repository.findById(id) //
        .map(orderDetails -> {
      	  orderDetails.setOrder(newOrderDetails.getOrder());
      	  orderDetails.setMeal(newOrderDetails.getMeal());
      	  orderDetails.setAmount(newOrderDetails.getAmount());
      	  orderDetails.setNumberofServing(newOrderDetails.getNumberOfServing());
      	  orderDetails.setToatalAmount(newOrderDetails.getTotalAmount());
      	  return repository.save(orderDetails);
        }) //
        .orElseGet(() -> {
          newOrderDetails.setId(id);
          return repository.save(newOrderDetails);
        });

    EntityModel<OrderDetails> entityModel = assembler.toModel(updatedOrderDetails);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }
  
  @DeleteMapping("/order-details/{id}")
  ResponseEntity<?> deleteCity(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
  
}