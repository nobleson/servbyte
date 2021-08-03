package com.servbyte.services.selfserviceapp.util;

public class ServiceProviderNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceProviderNotFoundException(Long id) {
		    super("Could not find service provider " + id);
		  }
}
