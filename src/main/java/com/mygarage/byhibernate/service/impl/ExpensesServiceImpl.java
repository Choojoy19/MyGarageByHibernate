package com.mygarage.byhibernate.service.impl;


import com.mygarage.byhibernate.model.Expenses;
import com.mygarage.byhibernate.repository.BaseRepository;
import com.mygarage.byhibernate.repository.impl.ExpensesRepositoryImpl;
import com.mygarage.byhibernate.service.BaseService;

import java.util.List;

public class ExpensesServiceImpl implements BaseService<Expenses> {
    private final BaseRepository<Expenses> repository = new ExpensesRepositoryImpl();
    @Override
    public List<Expenses> findAll() {
        return repository.findAll();
    }

    @Override
    public Expenses findById(long id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(Expenses entity) {
        return repository.create(entity);
    }

    @Override
    public boolean deleteById(long id) {
        return repository.deleteById(id);
    }
    @Override
    public Expenses update (Expenses entity){
        return repository.update(entity);
    }

}
