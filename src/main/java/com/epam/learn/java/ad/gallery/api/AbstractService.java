package com.epam.learn.java.ad.gallery.api;

public class AbstractService {
	protected ServiceProviderI sp;
	
	public AbstractService (ServiceProviderI sp) {
		this.sp = sp;
	}

	public ServiceProviderI getServiceProvider() {
		return sp;
	} 
	
	
	
}
