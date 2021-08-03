package com.servbyte.services.selfserviceapp.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServiceProvider {
	
	private @Id @GeneratedValue Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String city;
    private String logo;

    ServiceProvider() {}

    public ServiceProvider(String name, String phoneNumber, String email, String city, String logo) {
    	this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.city = city;
        this.logo = logo;
    }
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getEmail() {
        return this.email;
    }
    public String getCity() {
        return this.city;
    }
    public String getLogo() {
        return this.logo;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }
    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof ServiceProvider))
        return false;
      ServiceProvider provider = (ServiceProvider) o;
      return Objects.equals(this.id, provider.id) && Objects.equals(this.name, provider.name)
          && Objects.equals(this.phoneNumber, provider.phoneNumber) && Objects.equals(this.email, provider.email) 
          && Objects.equals(this.city, provider.city) && Objects.equals(this.logo, provider.logo);
    }

    @Override 
    public int hashCode() {
      return Objects.hash(this.id, this.name, this.phoneNumber, this.email, this.city, this.logo);
    }

    @Override
    public String toString() {
      return "ServiceProvider{" + "id=" + this.id + ", name='" + this.name + '\'' + ","
      		+ ", phone number=" + this.phoneNumber + '\'' + ", email=" +this.email +'\''
      		+ ", city=" +this.city + ", logo=" +this.logo + '\'' + '}';
    }
    
 }