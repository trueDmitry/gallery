package com.epam.learn.java.ad.gallery.web.model;

import com.epam.learn.java.ad.gallery.api.model.paging.Paginator;

public class ExpositionAllView {
	
	Paginator<ExpositionView> paginator;

	public Paginator<ExpositionView> getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator<ExpositionView> paginator) {
		this.paginator = paginator;
	}

}
