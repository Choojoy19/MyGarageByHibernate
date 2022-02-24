package com.mygarage.byhibernate.service.impl;

import com.mygarage.byhibernate.model.User;
import com.mygarage.byhibernate.repository.BaseUserRepository;
import com.mygarage.byhibernate.repository.impl.UserRepositoryImpl;
import com.mygarage.byhibernate.service.BaseUserService;

import java.util.List;
import java.util.Set;

public class UserServiceImpl implements BaseUserService<User> {
    private final BaseUserRepository<User> repository = new UserRepositoryImpl();
    @Override
    public Set<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(User entity) {
        return repository.create(entity);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }
    @Override
    public User update (User entity){
        return repository.update(entity);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }
}
