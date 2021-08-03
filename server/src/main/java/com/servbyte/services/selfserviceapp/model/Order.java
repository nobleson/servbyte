package com.servbyte.services.selfserviceapp.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.servbyte.services.selfserviceapp.util.Status;

@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order {

  private @Id @GeneratedValue Long id;

  private String description;
  private Status status;
  private String customer;
  private String orderDate;
  private String serviceProvider;
  private float totalAmount;

  Order() {}

  Order(String description, Status status, String customer, String serviceProvider, String orderDate, float totalAmount) {

    this.description = description;
    this.status = status;
    this.customer = customer;
    this.serviceProvider = serviceProvider;
    this.orderDate = orderDate;
    this.totalAmount = totalAmount;
  }

  public Long getId() {
    return this.id;
  }

  public String getDescription() {
    return this.description;
  }
  public Status getStatus() {
	    return this.status;
  }
  public String getCustmer() {
    return this.customer;
  }
  public String getServiceProvider() {
	    return this.serviceProvider;
  }
  public String getOrderDate() {
	    return this.orderDate;
  }
  public float getTotalAmount() {
	    return this.totalAmount;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public void setCustomer(String customer) {
	    this.customer = customer;
  }
  public void setServiceProvider(String serviceProvider) {
	    this.serviceProvider = serviceProvider;
  }
  public void setOrderDate(String orderDate) {
		    this.orderDate = orderDate;
  }
  public void setTotalAmount(float totalAmount) {
		    this.totalAmount = totalAmount;
  }
  
  
  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Order))
      return false;
    Order order = (Order) o;
    return Objects.equals(this.id, order.id) && Objects.equals(this.description, order.description)
    		&& Objects.equals(this.customer, order.customer) && Objects.equals(this.serviceProvider, order.serviceProvider)
    		&& Objects.equals(this.orderDate, order.orderDate) && this.totalAmount == order.totalAmount
            && this.status == order.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.description, this.status, this.customer, this.serviceProvider, this.orderDate, this.totalAmount);
  }

  @Override
  public String toString() {
    return "Order{" + "id=" + this.id + ", description='" + this.description + '\'' + ", status=" + this.status +", customer=" + this.customer +", service provider=" + this.serviceProvider +", order date=" + this.orderDate +", total amount=" + this.totalAmount +'}';
  }
}