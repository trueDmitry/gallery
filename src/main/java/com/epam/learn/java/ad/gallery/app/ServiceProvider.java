package com.epam.learn.java.ad.gallery.app;

import com.epam.learn.java.ad.gallery.api.DatabaseServiceI;
import com.epam.learn.java.ad.gallery.api.ExpositionServiceI;
import com.epam.learn.java.ad.gallery.api.PaymentServiceI;
import com.epam.learn.java.ad.gallery.api.SecurityServiceI;
import com.epam.learn.java.ad.gallery.api.ServiceProviderI;
import com.epam.learn.java.ad.gallery.api.ViewServiceI;

public class ServiceProvider implements ServiceProviderI{

	private ApplicationContext context;

	public ServiceProvider(ApplicationContext context) {
		this.context = context;
	}

	public SecurityServiceI getSecurityService() {
		return new SecurityService(context);
	}

	@Override
	public PaymentServiceI getPaymentService() {
		return new PaymentService(context, getDatabaseService());
	}

	@Override
	public ExpositionServiceI getExpositionService() {
		return new ExpositionService(context, getSecurityService(), getDatabaseService());
	}

	@Override
	public DatabaseServiceI getDatabaseService() {
		return new MysqlService();
	}

	@Override
	public ViewServiceI getViewService() {
		return new ViewService(context, getDatabaseService(), getExpositionService());
	}

}
