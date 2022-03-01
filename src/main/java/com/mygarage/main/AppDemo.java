package com.mygarage.main;
import com.mygarage.byhibernate.model.Car;
import com.mygarage.byhibernate.model.Role;
import com.mygarage.byhibernate.model.User;
import com.mygarage.byhibernate.service.BaseService;
import com.mygarage.byhibernate.service.UserService;
import com.mygarage.byhibernate.service.impl.CarServiceImpl;
import com.mygarage.byhibernate.service.impl.UserServiceImpl;

public class AppDemo {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        BaseService<Car> carService = new CarServiceImpl();


       User user = new User("Artyom","Yakubovich","Minsk",30);
       user.setRole(Role.ADMIN);
       user.setLogin("ADMIN");
       user.setPassword("password");
        userService.create(user);
        User user1 = new User("Art","Jak","Minsk",30);
        user1.setRole(Role.USER);
        user1.setLogin("ART");
        user1.setPassword("password");
        userService.create(user1);

    }
}
