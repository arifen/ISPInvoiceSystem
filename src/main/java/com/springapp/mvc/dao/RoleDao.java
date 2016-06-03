package com.springapp.mvc.dao;

import com.springapp.mvc.model.Role;

import java.util.List;

/**
 * Created by arifen on 6/1/16.
 */
public interface RoleDao {
    Role findByName(String name);
    List<Role> findAllRole();
}
