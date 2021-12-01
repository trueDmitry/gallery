package com.epam.learn.java.ad.gallery.api.model.paging;

import java.util.List;

public class Paginator<T> extends PagingDataAbstractModel<T> {

	private PaginSourceI<T> model;

	public Paginator(PaginSourceI<T> model) {
		this(model, 1, 10);
	}

	public Paginator(PaginSourceI<T> model, int page, int step) {
		super(page, step);
		this.model = model;
	}

	@Override
	public List<T> fetchData() throws PagingException {
		return model.fetchData(getStartIndex(), getPageStep());
	}

	@Override
	public int countTotal() throws PagingException {
		return model.countTotal();
	}

}