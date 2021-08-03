package com.servbyte.services.selfserviceapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.servbyte.services.selfserviceapp.jpa.ServiceProviderReposistory;
import com.servbyte.services.selfserviceapp.model.ServiceProvider;

@Configuration
class PreloadServiceProviders {

  private static final Logger log = LoggerFactory.getLogger(PreloadServiceProviders.class);

	@Bean
	CommandLineRunner initServiceProviders(ServiceProviderReposistory repository) {

	    return args -> {
	      log.info("Preloading " + repository.save(new ServiceProvider("KYC Restaurant", "08177228215","hello@kyc.com","Abuja","kyc.png")));
	      log.info("Preloading " + repository.save(new ServiceProvider("Chicken Republic", "09010060088","info@chickenrepublic.com", "Lagos","chicken.png")));
	    };
	}
}