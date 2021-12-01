package com.epam.learn.java.ad.gallery.api.model.paging;

import java.util.List;

public interface PagingDataModelI<T> {
	List<T> getData() throws PagingException;

	int getCount() throws PagingException;

	void setPage(int page);

	int getPage() throws PagingException;

	void setPageStep(int step);

	int getPageStep();

	int getPageCount() throws PagingException;

	boolean hasRightPages() throws PagingException;
}