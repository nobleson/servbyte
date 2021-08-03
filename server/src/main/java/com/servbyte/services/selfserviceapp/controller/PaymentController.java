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

import com.servbyte.services.selfserviceapp.jpa.PaymentRepository;
import com.servbyte.services.selfserviceapp.model.Payment;
import com.servbyte.services.selfserviceapp.model.PaymentModelAssembler;
import com.servbyte.services.selfserviceapp.util.PaymentNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class PaymentController {

  private final PaymentRepository repository;
  private final PaymentModelAssembler assembler;

  PaymentController(PaymentRepository repository, PaymentModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  
  
  // Aggregate root
  // end::get-aggregate-root[]
  
  @GetMapping("/payments")
  public CollectionModel<EntityModel<Payment>> all() {

    List<EntityModel<Payment>> payment = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(payment, linkTo(methodOn(PaymentController.class).all()).withSelfRel());
  }  

  // Single item
  @GetMapping("/payments/{id}")
  public EntityModel<Payment> one(@PathVariable Long id) {

	  Payment payment = repository.findById(id) //
        .orElseThrow(() -> new PaymentNotFoundException(id));

    return assembler.toModel(payment);
  }
  
  @PostMapping("/payments")
  ResponseEntity<?> newPayment(@RequestBody Payment newPayment) {

    EntityModel<Payment> entityModel = assembler.toModel(repository.save(newPayment));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  
  @PutMapping("/payments/{id}")
  ResponseEntity<?> replaceCity(@RequestBody Payment newPayment, @PathVariable Long id) {

	  Payment updatedPayment = repository.findById(id) //
        .map(payment -> {
      	  payment.setOrder(newPayment.getOrder());
      	  payment.setAmount(newPayment.getAmount());
      	  payment.setPayer(newPayment.getPayer());
      	  payment.setPaymentDate(newPayment.getPaymentDate());
      	  payment.setTransactionId(newPayment.getTransactionId());
      	  payment.setTransactionStatus(newPayment.getTransactionStatus());
      	  payment.setServiceProvider(newPayment.getServiceProvider());
          return repository.save(payment);
        }) //
        .orElseGet(() -> {
        	newPayment.setId(id);
          return repository.save(newPayment);
        });

    EntityModel<Payment> entityModel = assembler.toModel(updatedPayment);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }
  
  @DeleteMapping("/payment/{id}")
  ResponseEntity<?> deletePayment(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
  
}