package com.epam.learn.java.ad.gallery.web.exposition;



import com.epam.learn.java.ad.gallery.model.Exposition;
import com.epam.learn.java.ad.gallery.model.Room;
import com.epam.learn.java.ad.gallery.web.RequestHelper;
import com.epam.learn.java.ad.gallery.web.WebCommand;

public class Delete extends WebCommand {

	@Override
	protected void process() throws Exception {
		int id = new RequestHelper(request).getInt("id");
		
		serviceProvider.getExpositionService().delete(id);
		
		redirect(All.class);
	}

}
