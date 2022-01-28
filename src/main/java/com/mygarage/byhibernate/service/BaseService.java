package com.mygarage.byhibernate.service;

import java.util.List;

public interface BaseService<T> {
    List<T> findAll();
    T findById(long id);
    boolean create (T entity);
    boolean deleteById (long id);
    T update (T entity);


}
