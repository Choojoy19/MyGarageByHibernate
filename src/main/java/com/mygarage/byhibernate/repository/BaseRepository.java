package com.mygarage.byhibernate.repository;
import java.util.Set;

public interface BaseRepository<T> {
    Set<T> findAll();
    T findById(long id);
    boolean create(T entity);
    T update (T entity);
    boolean deleteById (long id);



}
