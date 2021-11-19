package com.epam.learn.java.ad.gallery.web.exposition;

import com.epam.learn.java.ad.gallery.api.PaymentServiceI;
import com.epam.learn.java.ad.gallery.app.PaymentService;
import com.epam.learn.java.ad.gallery.model.Exposition;
import com.epam.learn.java.ad.gallery.web.RequestHelper;
import com.epam.learn.java.ad.gallery.web.WebCommand;

public class Acquire extends WebCommand{

	@Override
	protected void process() throws Exception {
		int expoId = new RequestHelper(request).getId("id");
		if(expoId == 0 ) {
			redirect(All.class);
			return;  
		}
		
		PaymentServiceI ps = serviceProvider.getPaymentService();
		ps.buyExpositionTicket(expoId);
		
		redirect(Show.class, "id=" + expoId);
	}

}
