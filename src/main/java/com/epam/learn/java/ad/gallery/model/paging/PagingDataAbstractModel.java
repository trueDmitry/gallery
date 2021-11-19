package com.epam.learn.java.ad.gallery.model.paging;

import java.util.List;

abstract class PagingDataAbstractModel <T> implements PagingDataModelI<T> {

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

  public List<T> getData() {
    if (data == null) {
      data = fetchData();
    }
    return data;
  }

  public int getCount() {
    if (count == null ) {
      count = countTotal();
    }
    return count;
  }

  protected abstract List<T> fetchData();
  
  protected abstract int countTotal();

  public void setPageStep(int step) {
    this.step = step;
  }

  public int getPageStep() {
    return step;
  }

  public void setPage(int page) {
    this.page = page;
  }

  private void validateCurPage() {
    int pageCount = getPageCount();
    if (page > pageCount) {
      page = pageCount;
    }
  }

  public int getPage() {
    validateCurPage();
    return page;
  }

  public int getPageCount() {
	return (int)Math.round(Math.ceil(getCount() / getPageStep()));
  }

  public int  getStartIndex() {
    page = getPage();
    return (page > 0 ? page - 1 : 0) * step;
  }

  public boolean hasRightPages() {
    return getStartIndex() + step < getCount();
  }

  public boolean hasLeftPages() {
    return getPage() > 1;
  }

  public int getStartNumber() {
    return getStartIndex() + 1;
  }

  public int getEndNumber() {
    return getStartIndex() + getDataCount();
  }

  protected int getDataCount() {
    return getData().size();
  }

}