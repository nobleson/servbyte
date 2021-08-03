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

import com.servbyte.services.selfserviceapp.jpa.MealRepository;
import com.servbyte.services.selfserviceapp.model.Meal;
import com.servbyte.services.selfserviceapp.model.MealModelAssembler;
import com.servbyte.services.selfserviceapp.util.MealNotFounException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600)
@RestController
public class MealController {

  private final MealRepository repository;
  private final MealModelAssembler assembler;

  MealController(MealRepository repository, MealModelAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

  
  
  // Aggregate root
  // end::get-aggregate-root[]
  
  @GetMapping("/meals")
  public CollectionModel<EntityModel<Meal>> all() {

    List<EntityModel<Meal>> meals = repository.findAll().stream() //
        .map(assembler::toModel) //
        .collect(Collectors.toList());

    return CollectionModel.of(meals, linkTo(methodOn(MealController.class).all()).withSelfRel());
  }  

  // Single item
  @GetMapping("/meals/{id}")
  public EntityModel<Meal> one(@PathVariable Long id) {

	  Meal meal = repository.findById(id) //
        .orElseThrow(() -> new MealNotFounException(id));

    return assembler.toModel(meal);
  }
  
  @PostMapping("/meals")
  ResponseEntity<?> newMeal(@RequestBody Meal newMeal) {

    EntityModel<Meal> entityModel = assembler.toModel(repository.save(newMeal));

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }

  
  @PutMapping("/meals/{id}")
  ResponseEntity<?> replaceMeal(@RequestBody Meal newMeal, @PathVariable Long id) {

	  Meal updatedMeal = repository.findById(id) //
        .map(meal -> {
      	  meal.setName(newMeal.getName());
      	  meal.setDescription(newMeal.getDescription());
      	  meal.setPhoto(newMeal.getPhoto());
      	  meal.setPrice(newMeal.getPrice());
          return repository.save(meal);
        }) //
        .orElseGet(() -> {
          newMeal.setId(id);
          return repository.save(newMeal);
        });

    EntityModel<Meal> entityModel = assembler.toModel(updatedMeal);

    return ResponseEntity //
        .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
        .body(entityModel);
  }
  
  @DeleteMapping("/meals/{id}")
  ResponseEntity<?> deleteMeal(@PathVariable Long id) {

    repository.deleteById(id);

    return ResponseEntity.noContent().build();
  }
  
}