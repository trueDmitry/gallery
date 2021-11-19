package  com.epam.learn.java.ad.gallery.model.paging;

import java.util.List;

interface PaginSourceI <T> {
  List<T> fetchData(int startIndex, int quantity);
  int countTotal();
}