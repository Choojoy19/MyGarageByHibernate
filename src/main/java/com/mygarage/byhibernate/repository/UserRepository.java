package com.mygarage.byhibernate.repository;
import com.mygarage.byhibernate.model.User;

public interface UserRepository extends BaseRepository<User>{

    User findByLoginAndPassword(String login, String password);
    boolean isExistByLogin (String login);

}
