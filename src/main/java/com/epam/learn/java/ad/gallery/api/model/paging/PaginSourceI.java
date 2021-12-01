package  com.epam.learn.java.ad.gallery.api.model.paging;

import java.util.List;

public interface PaginSourceI <T> {
  List<T> fetchData(int startIndex, int quantity) throws PagingException;
  int countTotal() throws PagingException;
}