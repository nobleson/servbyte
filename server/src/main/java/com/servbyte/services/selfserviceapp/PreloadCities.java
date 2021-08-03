package com.servbyte.services.selfserviceapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.servbyte.services.selfserviceapp.jpa.CityRepository;
import com.servbyte.services.selfserviceapp.model.City;

@Configuration
class PreloadCities {

  private static final Logger log = LoggerFactory.getLogger(PreloadCities.class);

	@Bean
	CommandLineRunner initCities(CityRepository repository) {

	    return args -> {
	      log.info("Preloading " + repository.save(new City("Aba")));
	      log.info("Preloading " + repository.save(new City("Abuja")));
	      log.info("Preloading " + repository.save(new City("Asaba")));
	      log.info("Preloading " + repository.save(new City("Enugu")));
	      log.info("Preloading " + repository.save(new City("Ibadan")));
	      log.info("Preloading " + repository.save(new City("Lagos")));
	      log.info("Preloading " + repository.save(new City("Kano")));
	      log.info("Preloading " + repository.save(new City("Onitsha")));
	      log.info("Preloading " + repository.save(new City("Owerri")));
	      log.info("Preloading " + repository.save(new City("Port Harcourt")));
	      log.info("Preloading " + repository.save(new City("Umuahia")));
	      log.info("Preloading " + repository.save(new City("Uyo")));
	    };
	}
}

