package com.servbyte.services.selfserviceapp.util;


public class OrderDetailsNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public OrderDetailsNotFoundException(Long id) {
		    super("Could not find order details " + id);
	}
}

