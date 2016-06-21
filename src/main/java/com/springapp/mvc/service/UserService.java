package com.springapp.mvc.service;

import com.springapp.mvc.model.User;

import java.util.List;

/**
 * Created by arifen on 5/26/16.
 */
public interface UserService {

    void saveUser(User user);

    List<User> findUserByloginId(String loginId);
    User findByUserId(Long userId);
}
