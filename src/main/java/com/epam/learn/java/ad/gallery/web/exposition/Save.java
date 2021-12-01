package com.epam.learn.java.ad.gallery.web.exposition;

import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.Room;
import com.epam.learn.java.ad.gallery.web.HttpCode;
import com.epam.learn.java.ad.gallery.web.RequestHelper;
import com.epam.learn.java.ad.gallery.web.WebCommand;

public class Save extends WebCommand {

	@Override
	protected void process() throws Exception {
		// TODO  
		Exposition expo = getRequestExposition();
		if (expo == null) {
			httpCode = HttpCode.UNPROCESSABLE_ENTRY; 
			return;
		}
		
		String idParam = request.getParameter("id"); 
		
		if (serviceProvider.getExpositionService().store(expo)) {
			idParam = String.valueOf(expo.getId()); 
		}
		redirect(Edit.class, "id=" + idParam);
	}

	private Exposition getRequestExposition() {
		// TODO add specific errors
		Exposition expo;
		try {
			RequestHelper r = new RequestHelper(request);
			expo = new Exposition();
			expo.setId(r.getInt("id"));
			expo.setTheme(r.getString("theme"));
			expo.setPrice(r.getInt("price"));
			expo.setStart(r.getDate("start"));
			expo.setEnd(r.getDate("end"));
			expo.setOpen(r.getInt("open"));
			expo.setClose(r.getInt("close"));

			r.getIds("room").forEach(id -> {
				expo.addRoom(new Room(id));
			});
			expo.setPublished((r.getBoolean("published")));
		} catch (Exception e) {
			return null;
		}
		return expo;
	}

}
