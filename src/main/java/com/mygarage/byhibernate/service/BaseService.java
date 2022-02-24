package com.mygarage.byhibernate.service;

import java.util.List;
import java.util.Set;

public interface BaseService<T> {
    Set<T> findAll();
    T findById(long id);
    boolean create (T entity);
    boolean deleteById (long id);
    T update (T entity);


}
