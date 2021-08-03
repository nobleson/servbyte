package com.servbyte.services.selfserviceapp.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	
	private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String contactAddress;
    private String city;


    Customer() {}

    public Customer(String firstName, String lastName, String phoneNumber, String emailAddress, String contactAddress, String city) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.phoneNumber = phoneNumber;
    	this.emailAddress = emailAddress;
    	this.contactAddress = contactAddress;
    	this.city = city;
    }
    
    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public String getEmailAddress() {
        return this.emailAddress;
    }
    public String getContactAddress() {
        return this.contactAddress;
    }
    public String getCity() {
        return this.city;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }
    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof Customer))
        return false;
      Customer customer = (Customer) o;
      return Objects.equals(this.id, customer.id) && Objects.equals(this.firstName, customer.firstName)
    		  && Objects.equals(this.lastName, customer.lastName) && Objects.equals(this.phoneNumber, customer.phoneNumber)
    		  && Objects.equals(this.emailAddress, customer.emailAddress) && Objects.equals(this.contactAddress, customer.contactAddress)
    		  && Objects.equals(this.city, customer.city);
    }

    @Override 
    public int hashCode() {
      return Objects.hash(this.id, this.firstName, this.lastName, this.phoneNumber, this.emailAddress, this.contactAddress, this.city);
    }

    @Override
    public String toString() {
      return "City{" + "id=" + this.id + ", name='" + this.firstName + '\'' + this.lastName + '\'' + this.phoneNumber + '\'' + this.emailAddress + '\'' + this.contactAddress + '\'' + this.city +'}';
    }
}