package com.mygarage.byhibernate.service;

import com.mygarage.byhibernate.model.User;

public interface UserService extends BaseService<User>{
    User findByLoginAndPassword(String login, String password);
    boolean isExistByLogin (String login);
}
