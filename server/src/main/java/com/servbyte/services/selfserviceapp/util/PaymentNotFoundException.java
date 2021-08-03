package com.servbyte.services.selfserviceapp.util;


public class PaymentNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException(Long id) {
		    super("Could not find city " + id);
		  }
}

