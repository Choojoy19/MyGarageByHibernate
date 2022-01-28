package com.mygarage.byhibernate.repository;


public interface BaseUserRepository<T> extends BaseRepository<T>{

    T findByLoginAndPassword(String login, String password);

}
