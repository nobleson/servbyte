package com.servbyte.services.selfserviceapp.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class City {
	
	private @Id @GeneratedValue Long id;
    private String name;


    City() {}

    public City(String name) {
    	this.name = name;
    }
    
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof City))
        return false;
      City city = (City) o;
      return Objects.equals(this.id, city.id) && Objects.equals(this.name, city.name);
    }

    @Override 
    public int hashCode() {
      return Objects.hash(this.id, this.name);
    }

    @Override
    public String toString() {
      return "City{" + "id=" + this.id + ", name='" + this.name + '\'' + '}';
    }
}