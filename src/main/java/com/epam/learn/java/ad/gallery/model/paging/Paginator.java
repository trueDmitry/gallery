package com.epam.learn.java.ad.gallery.model.paging;

import java.util.List;

class Paginator<T> extends PagingDataAbstractModel<T> {

	private PaginSourceI<T> model;

	public Paginator(PaginSourceI<T> model ) {
	  this(model , 1, 10);
  }

	public Paginator(PaginSourceI<T> model , int page , int step) {
	    super(page, step);
	    this.model = model;
	  }

	@Override
	protected  List<T> fetchData() {
		return model.fetchData(getStartIndex(), getPageStep());
	}

	@Override
	protected int countTotal() {
		return model.countTotal();
	}

}