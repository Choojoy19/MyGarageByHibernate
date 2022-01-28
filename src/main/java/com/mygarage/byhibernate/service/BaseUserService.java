package com.mygarage.byhibernate.service;

public interface BaseUserService<T> extends BaseService<T>{
    T findByLoginAndPassword(String login, String password);
}
