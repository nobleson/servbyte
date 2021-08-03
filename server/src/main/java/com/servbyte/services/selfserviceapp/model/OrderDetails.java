package com.servbyte.services.selfserviceapp.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_ORDER_DETAILS")
public class OrderDetails {
	
	private @Id @GeneratedValue Long id;
    private String order;
    private String meal;
    private float amount;
    private int numberOfServing; // the number of orders
    private float totalAmount;

    OrderDetails() {}

    public OrderDetails(String order,String meal,float amount,int numberOfServing,float totalAmount) {
    	this.order = order;
    	this.meal = meal;
    	this.amount = amount;
    	this.numberOfServing = numberOfServing;
    	this.totalAmount = totalAmount;
    	
    }
    
    public Long getId() {
        return this.id;
    }

    public String getOrder() {
        return this.order;
    }
    public String getMeal() {
        return this.meal;
    }
    public float getAmount() {
        return this.amount;
    }
    public int getNumberOfServing() {
        return this.numberOfServing;
    }
    public float getTotalAmount() {
        return this.totalAmount;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public void setMeal(String meal) {
        this.meal = meal;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public void setNumberofServing(int numberOfServing) {
        this.numberOfServing = numberOfServing;
    }
    public void setToatalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof OrderDetails))
        return false;
      OrderDetails orderDetail = (OrderDetails) o;
      return Objects.equals(this.id, orderDetail.id) && Objects.equals(this.order, orderDetail.order)
    		  && Objects.equals(this.meal, orderDetail.meal)&& this.amount == orderDetail.amount
    		  && this.numberOfServing == orderDetail.numberOfServing && this.totalAmount == orderDetail.totalAmount;
    }

    @Override 
    public int hashCode() {
      return Objects.hash(this.id, this.order, this.meal, this.amount, this.numberOfServing, this.totalAmount);
    }

    @Override
    public String toString() {
      return "OrderDetails{" + "id=" + this.id + ", order='" + this.order + '\'' + ", meal='" + this.meal + '\'' + ", amount='" + this.amount + '\'' + ", number of serving='" + this.numberOfServing + '\'' + ", total amount='" + this.totalAmount + '\'' + '}';
    }
}