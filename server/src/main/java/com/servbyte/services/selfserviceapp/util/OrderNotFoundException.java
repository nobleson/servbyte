package com.servbyte.services.selfserviceapp.util;



public class OrderNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(Long id) {
		    super("Could not find order " + id);
		  }
}

