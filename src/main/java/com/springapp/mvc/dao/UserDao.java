package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;

import java.util.List;

/**
 * Created by arifen on 5/26/16.
 */
public interface UserDao {

    void addUser(User user);
    List<User> findUserByLoginId(String loginId);
}
