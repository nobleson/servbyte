package com.servbyte.services.selfserviceapp.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servbyte.services.selfserviceapp.model.ServiceProvider;

@Repository
public interface ServiceProviderReposistory extends JpaRepository<ServiceProvider, Long>{
	

}
