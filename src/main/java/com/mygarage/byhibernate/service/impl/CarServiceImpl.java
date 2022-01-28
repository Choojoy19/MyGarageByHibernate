package com.mygarage.byhibernate.service.impl;

import com.mygarage.byhibernate.model.Car;
import com.mygarage.byhibernate.model.User;
import com.mygarage.byhibernate.repository.BaseRepository;
import com.mygarage.byhibernate.repository.impl.CarRepositoryImpl;
import com.mygarage.byhibernate.repository.impl.UserRepositoryImpl;
import com.mygarage.byhibernate.service.BaseService;

import java.util.List;

public class CarServiceImpl implements BaseService<Car> {
    private final BaseRepository<Car> repository = new CarRepositoryImpl();
    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    @Override
    public Car findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(Car entity) {
        return repository.create(entity);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }
    @Override
    public Car update (Car entity){
        return repository.update(entity);
    }

}
