package com.servbyte.services.selfserviceapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.servbyte.services.selfserviceapp.jpa.MealRepository;
import com.servbyte.services.selfserviceapp.model.Meal;


@Configuration
class PreloadMeals {

  private static final Logger log = LoggerFactory.getLogger(PreloadMeals.class);

	@Bean
	CommandLineRunner initMeals(MealRepository repository) {

	    return args -> {
	      log.info("Preloading " + repository.save(new Meal("Jellof Rice","Rice  cooked in a rich tomato sauce and soaked in all the flavors",35,"jellof-rice.jpg",1500)));
	    };
	}
}
