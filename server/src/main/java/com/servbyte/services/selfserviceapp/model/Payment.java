package com.servbyte.services.selfserviceapp.model;



import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENTS")
public class Payment {
	
	private @Id @GeneratedValue Long id;
    private String order;
    private float amount;
    private String payer;
    private String paymentDate;
    private String transactionId;
    private int transactionStatus;
    private String serviceProvider;

    Payment() {}

    public Payment(String order, float amount, String payer, String paymentDate, String transactionId, int transactionStatus, String serviceProvider) {
    	this.order = order;
    	this.amount = amount;
    	this.payer = payer;
    	this.paymentDate = paymentDate;
    	this.transactionId = transactionId;
    	this.transactionStatus = transactionStatus;
    	this.serviceProvider = serviceProvider;
    	
    }
    
    public Long getId() {
        return this.id;
    }

    public String getOrder() {
        return this.order;
    }
    public float getAmount() {
        return this.amount;
    }
    public String getPayer() {
        return this.payer;
    }
    public String getPaymentDate() {
        return this.paymentDate;
    }
    public String getTransactionId() {
        return this.transactionId;
    }
    public int getTransactionStatus() {
        return this.transactionStatus;
    }
    public String getServiceProvider() {
        return this.serviceProvider;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public void setAmount(float amount) {
        this.amount = amount;
    }
    public void setPayer(String payer) {
        this.payer = payer;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof Payment))
        return false;
      Payment payment = (Payment) o;
      return Objects.equals(this.id, payment.id) && Objects.equals(this.order, payment.order)
    		  && Objects.equals(this.payer, payment.payer) && Objects.equals(this.paymentDate, payment.paymentDate)
    		  && Objects.equals(this.transactionId,  payment.transactionId) && this.transactionStatus == payment.transactionStatus
    		  && Objects.equals(this.serviceProvider, payment.serviceProvider);
    }

    @Override 
    public int hashCode() {
      return Objects.hash(this.id, this.order, this.payer, this.paymentDate, this.transactionId, this.transactionStatus,this.serviceProvider);
    }

    @Override
    public String toString() {
      return "Payment{" + "id=" + this.id + ", order='" + this.order + '\'' + ", payer='" + this.payer + '\'' + ", payment date='" + this.paymentDate + '\'' + ", transactionId='" + this.transactionId + '\'' + ", transactionStatus='" + this.transactionStatus + '\'' + ", serviceProvider='" + this.serviceProvider + '\'' + '}';
    }
}