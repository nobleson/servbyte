package com.servbyte.services.selfserviceapp.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servbyte.services.selfserviceapp.model.City;



@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	

}