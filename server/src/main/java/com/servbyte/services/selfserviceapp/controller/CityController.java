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

import com.servbyte.services.selfserviceapp.jpa.CityRepository;
import com.servbyte.services.selfserviceapp.model.City;
import com.servbyte.services.selfserviceapp.model.CityModeAssembler;
import com.servbyte.services.selfserviceapp.util.CityNotFoundException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class CityController {

  private final CityRepository repository;
  private final CityModeAssembler assembler;

  CityController(CityRepository repository, CityModeAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  
  
  // Aggregate root
  // end::get-aggregate-root[]
  
  @GetMapping("/city")
  public CollectionModel<EntityModel<City>> all() {

    List<EntityModel<City>> city = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(city, linkTo(methodOn(CityController.class).all()).withSelfRel());
  }  

  // Single item
  @GetMapping("/city/{id}")
  public EntityModel<City> one(@PathVariable Long id) {

	  City city = repository.findById(id) //
        .orElseThrow(() -> new CityNotFoundException(id));

    return assembler.toModel(city);
  }
  
  @PostMapping("/city")
  ResponseEntity<?> newCity(@RequestBody City newCity) {

    EntityModel<City> entityModel = assembler.toModel(repository.save(newCity));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  
  @PutMapping("/city/{id}")
  ResponseEntity<?> replaceCity(@RequestBody City newCity, @PathVariable Long id) {

	  City updatedCity = repository.findById(id) //
        .map(city -> {
      	  city.setName(newCity.getName());
          return repository.save(city);
        }) //
        .orElseGet(() -> {
          newCity.setId(id);
          return repository.save(newCity);
        });

    EntityModel<City> entityModel = assembler.toModel(updatedCity);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }
  
  @DeleteMapping("/city/{id}")
  ResponseEntity<?> deleteCity(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
  
}