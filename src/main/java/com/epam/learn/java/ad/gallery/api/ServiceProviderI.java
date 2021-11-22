package com.epam.learn.java.ad.gallery.api;

public interface ServiceProviderI {

	SecurityServiceI getSecurityService();

	PaymentServiceI getPaymentService();
	
	ExpositionServiceI getExpositionService();
	
	DatabaseServiceI getDatabaseService();

	ViewServiceI getViewService();

}
