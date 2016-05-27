package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;

/**
 * Created by arifen on 5/26/16.
 */
public interface UserDao {

    void addUser(User user);
    User findUserByLoginId(String loginId);
}
