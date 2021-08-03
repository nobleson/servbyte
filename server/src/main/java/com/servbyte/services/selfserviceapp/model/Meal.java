package com.servbyte.services.selfserviceapp.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Meal {
	
	private @Id @GeneratedValue Long id;
    private String name;
    private String description;
    private int duration;
    private String photo;
    private float price;

    Meal() {}

    public Meal(String name, String description, int duration, String photo, float price) {
    	this.name = name;
        this.description = description;
        this.duration = duration;
        this.photo = photo;
        this.price = price;
    }
    
    
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public int getDuration() {
        return this.duration;
    }
    public String getPhoto() {
        return this.photo;
    }
    public float getPrice() {
        return this.price;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    
    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof ServiceProvider))
        return false;
      Meal meal = (Meal) o;
      return Objects.equals(this.id, meal.id) && Objects.equals(this.name, meal.name)
          && Objects.equals(this.description, meal.description) && Objects.equals(this.duration, meal.duration) 
          && Objects.equals(this.photo, meal.photo) && Objects.equals(this.price, meal.price);
    }

    @Override 
    public int hashCode() {
      return Objects.hash(this.id, this.name, this.description, this.duration, this.photo, this.price);
    }

    @Override
    public String toString() {
      return "Meal{" + "id=" + this.id + ", name='" + this.name + '\'' + ","
      		+ ", description=" + this.description + '\'' + ", duration=" +this.duration +'\''
      		+ ", photo=" +this.photo + ", price=" +this.price + '\'' + '}';
    }
}