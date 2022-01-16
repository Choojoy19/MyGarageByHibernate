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
        BaseService<User> userService = new UserServiceImpl();
        BaseService<Car> carService = new CarServiceImpl();
        List<Car> carsList = new ArrayList<>();
        List<Car> carsList1 = new ArrayList<>();

       User user = new User("Artyom","Yak","Minsk",30);
       user.setRole(Role.ADMIN);
       user.setLogin("ADMIN");
       user.setPassword("password");
       Car car = new Car();
       car.setBrand("BMW");
       car.setModel("520");
      // car.setUser(user);
        Car car1 = new Car();
        car1.setBrand("Mercedes");
        car1.setModel("e211");
        //car1.setUser(user);
        carsList.add(car);
        carsList.add(car1);
       user.setCars(carsList);
       userService.create(user);
        User user1 = new User("Max","Jak","Minsk",30);
        user1.setRole(Role.USER);
        user1.setLogin("MAX");
        user1.setPassword("password");
        Car car3 = new Car();
        car3.setBrand("audi");
        car3.setModel("a6");
       // car3.setUser(user1);
        Car car4 = new Car();
        car4.setBrand("Lada");
        car4.setModel("2109");
        //car4.setUser(user1);
        carsList1.add(car3);
        carsList1.add(car4);
        user1.setCars(carsList1);
        userService.create(user1);

    }
}
