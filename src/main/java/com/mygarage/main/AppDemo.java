package com.mygarage.main;

import com.mygarage.byhibernate.model.Car;
import com.mygarage.byhibernate.model.Role;
import com.mygarage.byhibernate.model.User;
import com.mygarage.byhibernate.service.BaseService;
import com.mygarage.byhibernate.service.impl.CarServiceImpl;
import com.mygarage.byhibernate.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class AppDemo {
    public static void main(String[] args) {
        BaseService<User> service = new UserServiceImpl();
        BaseService<Car> service1 = new CarServiceImpl();
        List<Car> carsList = new ArrayList<>();

       User user = new User("Artyom","Yak","Minsk",30);
       user.setRole(Role.ADMIN);
       user.setLogin("ADMIN");
       user.setPassword("password");
       service.create(user);
       Car car = new Car();
       car.setBrand("BMW");
       car.setModel("520");
        Car car1 = new Car();
        car1.setBrand("Mercedes");
        car1.setModel("e211");
        service1.create(car);
        service1.create(car1);
        carsList.add(car);
        carsList.add(car1);
       user.setCars(carsList);


    }
}
