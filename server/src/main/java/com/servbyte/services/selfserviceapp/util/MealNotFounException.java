package com.servbyte.services.selfserviceapp.util;

public class MealNotFounException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public MealNotFounException(Long id) {
		    super("Could not find meal " + id);
		  }
}

