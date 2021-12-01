package com.epam.learn.java.ad.gallery.api.model.paging;

import java.util.List;

public abstract class PagingDataAbstractModel <T> implements PagingDataModelI<T> {

  protected int page;
  protected int step;

  protected Integer count;
  protected List<T> data;

  public PagingDataAbstractModel() {
	  page = 1;
	  step = 10;
  }
  
  public PagingDataAbstractModel(int page, int step) {
    this.step = step;
    this.page = page < 1 ? 1 : page;
  }

  public List<T> getData() throws PagingException {
    if (data == null) {
      data = fetchData();
    }
    return data;
  }

  public int getCount() throws PagingException {
    if (count == null ) {
      count = countTotal();
    }
    return count;
  }

  protected abstract List<T> fetchData() throws PagingException;
  
  protected abstract int countTotal() throws PagingException;

  public void setPageStep(int step) {
    this.step = step;
  }

  public int getPageStep() {
    return step;
  }

  public void setPage(int page) {
    this.page = page;
  }

  private void validateCurPage() throws PagingException {
    int pageCount = getPageCount();
    if (page > pageCount) {
      page = pageCount;
    }
  }

  public int getPage() throws PagingException {
    validateCurPage();
    return page;
  }

  public int getPageCount() throws PagingException {
	return (int)Math.round(Math.ceil((float) getCount() / getPageStep()));
  }

  public int  getStartIndex() throws PagingException {
    page = getPage();
    return (page > 0 ? page - 1 : 0) * step;
  }

  public boolean hasRightPages() throws PagingException {
    return getStartIndex() + step < getCount();
  }

  public boolean hasLeftPages() throws PagingException {
    return getPage() > 1;
  }

  public int getStartNumber() throws PagingException {
    return getStartIndex() + 1;
  }

  public int getEndNumber() throws PagingException {
    return getStartIndex() + getDataCount();
  }

  protected int getDataCount() throws PagingException {
    return getData().size();
  }

}