package com.epam.learn.java.ad.gallery.model.paging;

import java.util.List;

interface PagingDataModelI<T> {
	List<T> getData();

	int getCount();

	void setPage(int page);

	int getPage();

	void setPageStep(int step);

	int getPageStep();

	int getPageCount();

	boolean hasRightPages();
}