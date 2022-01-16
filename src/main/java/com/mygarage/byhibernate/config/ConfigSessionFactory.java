package com.mygarage.byhibernate.config;


import com.mygarage.byhibernate.model.Car;
import com.mygarage.byhibernate.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class ConfigSessionFactory {
    private static SessionFactory sessionFactory;

    private ConfigSessionFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/mygarage?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Car.class);

                sessionFactory = configuration
                        .buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                                .build());

            } catch (Exception e) {
                throw new RuntimeException("There is issue in hibernate util");
            }
        }
        return sessionFactory;
    }
}
